package scala_app.logic

import protocol.*

import Request.Payload as Req
import Response.Payload as Res
import scalapb.GeneratedMessage
import twotm8.*

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
    case Req.Login(value) =>
      val login = value.login.trim
      if login.isEmpty then Answer.Error("empty login!")
      else if value.password.isEmpty then Answer.Error("empty password!")
      else

        val token =
          state.client.login(Nickname(login), Password(value.password))

        state.respond(
          Res.Login(
            Login.Response(Login.Response.Payload.Token(token.jwt.raw))
          )
        )

      end if

    case Req.GetState(value) =>
      state.respond(Res.GetState(GetState.Response(state = Some(state.get))))
  end match
end handleRequest

@main def hello =
  handleRequest(StateManager(State()), Request.of(Request.Payload.Login(Login.Request("anton", "bla"))))
