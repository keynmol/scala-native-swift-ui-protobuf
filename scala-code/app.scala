package scala_app.logic

import protocol.*

import Request.Payload as Req
import Response.Payload as Res
import scalapb.GeneratedMessage
import twotm8.*
import twotm8.api.ErrorInfo
import scribe.Level
import scribe.format.Formatter

enum Answer:
  case Error(msg: String, code: protocol.ERROR_CODE = protocol.ERROR_CODE.OTHER)
  case Ok[T <: GeneratedMessage](value: T)

class StateManager(private var st: State):

  val client = twotm8.client.Client.create("https://twotm8.com")

  def respond[T <: Response.Payload](msg: T) =
    Answer.Ok(Response(payload = msg))

  def stateChange[T <: Response.Payload](msg: T, change: State => State) =
    st.synchronized:
      st = change(st)
      st
    Answer.Ok(Response(payload = msg))

  def get: State = st
end StateManager

def handleRequest(state: StateManager, req: Request): Answer =
  if req.payload.isDefined then scribe.info(s"Handling request: ${req.payload}")
  req.payload match
    case Req.Empty => Answer.Error("payload was empty")
    case Req.SendTwot(req) =>
      val token = JWT(req.token)
      state.client.create_twot(req.text, token) match
        case Left(ErrorInfo.Unauthorized(msg)) =>
          Answer.Error(msg, ERROR_CODE.UNAUTHORIZED)
        case Left(value)  => Answer.Error(value.message)
        case Right(value) => state.respond(Res.SendTwot(SendTwot.Response()))

    case Req.SetOptions(value) =>
      val minLevel = value.options
        .map(o => if o.debugLogging then Level.Debug else Level.Info)
        .getOrElse(Level.Info)

      scribe.Logger.root
        .clearHandlers()
        .withHandler(
          writer = scribe.writer.SystemErrWriter,
          formatter = Formatter.classic
        )
        .withMinimumLevel(minLevel)
        .replace()

      state.respond(Res.SetOptions(SetOptions.Response()))

    case Req.GetMe(req) =>
      val token = JWT(req.token)

      state.client.me(token) match
        case Left(ErrorInfo.Unauthorized(msg)) =>
          Answer.Error(msg, ERROR_CODE.UNAUTHORIZED)
        case Left(other) => Answer.Error(other.message)
        case Right(leader) =>
          state.respond(
            Res.GetMe(
              GetMe.Response(
                Some(
                  Me(
                    id = leader.id.raw.toString,
                    nickname = leader.nickname.raw
                  )
                )
              )
            )
          )

      end match

    case Req.GetWall(req) =>
      state.client.wall(JWT(req.token)) match
        case Left(value) => Answer.Error(value.message)
        case Right(value) =>
          val protoTwots =
            value.map: twot =>
              protocol.Twot(
                author = twot.authorNickname.raw,
                text = twot.content.raw,
                id = twot.id.raw.toString()
              )

          val wall = Wall(twots = protoTwots)

          state.respond(
            Res.GetWall(GetWall.Response(Some(wall)))
          )

    case Req.Login(value) =>
      val login = value.login.trim
      if login.isEmpty then Answer.Error("empty login!")
      else if value.password.isEmpty then Answer.Error("empty password!")
      else
        state.client.login(Nickname(login), Password(value.password)) match
          case Left(ErrorInfo.InvalidCredentials()) =>
            Answer.Error("invalid credentials", ERROR_CODE.INVALID_CREDENTIALS)
          case Left(other) => Answer.Error(other.message)

          case Right(value) =>
            state.respond(
              Res.Login(
                Login.Response(value.jwt.raw)
              )
            )

      end if

  end match
end handleRequest
