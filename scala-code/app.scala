package scala_app.logic

import protocol.*

import Request.Payload as Req
import Response.Payload as Res
import scalapb.GeneratedMessage
import twotm8.*
import twotm8.api.ErrorInfo

enum Answer:
  case Error(msg: String)
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
    case Req.GetMe(req) =>
      val token = JWT(req.token)

      state.client.me(token) match
        case Left(ErrorInfo.Unauthorized(_)) =>
          state.respond(
            Res.GetMe(
              GetMe.Response(
                GetMe.Response.Payload.Err(
                  GetMe.ERROR_CODE.UNAUTHORIZED
                )
              )
            )
          )
        case Left(other) => Answer.Error(other.message)
        case Right(leader) =>
          state.respond(
            Res.GetMe(
              GetMe.Response(
                GetMe.Response.Payload.Me(
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
            Res.GetWall(GetWall.Response(GetWall.Response.Payload.Wall(wall)))
          )

    case Req.Login(value) =>
      val login = value.login.trim
      if login.isEmpty then Answer.Error("empty login!")
      else if value.password.isEmpty then Answer.Error("empty password!")
      else
        state.client.login(Nickname(login), Password(value.password)) match
          case Left(ErrorInfo.InvalidCredentials()) =>
            state.respond(
              Res.Login(
                Login.Response(
                  Login.Response.Payload.Err(
                    Login.ERROR_CODE.INVALID_CREDENTIALS
                  )
                )
              )
            )

          case Left(other) => Answer.Error(other.message)

          case Right(value) =>
            state.respond(
              Res.Login(
                Login.Response(Login.Response.Payload.Token(value.jwt.raw))
              )
            )

      end if

    case Req.GetState(value) =>
      state.respond(Res.GetState(GetState.Response(state = Some(state.get))))
  end match
end handleRequest
