package scala_app.logic

import protocol.*

import Request.Payload as Req
import Response.Payload as Res
import scalapb.GeneratedMessage

enum Answer:
  case Error(msg: String)
  case Ok[T <: GeneratedMessage](value: T)

class StateManager(private var st: State):

  def respond[T <: Response.Payload](msg: T) =
    Answer.Ok(Response(payload = msg))

  def stateChange[T <: Response.Payload](msg: T, change: State => State) =
    st.synchronized:
      st = change(st)
      st
    Answer.Ok(Response(payload = msg, stateChanged = true))

  def get: State = st
end StateManager

def handleRequest(state: StateManager, req: Request): Answer =
  if req.payload.isDefined then scribe.info(s"Handling request: ${req.payload}")
  req.payload match
    case Req.Empty => Answer.Error("payload was empty")
    case Req.AddNumber(value) =>
      if value.amount < 0 then Answer.Error("number can't be negative!")
      else
        state.stateChange(
          Res.AddNumber(AddNumber.Response()),
          state => state.copy(sum = state.sum + value.amount)
        )

    case Req.AddString(value) =>
      state.stateChange(
        Res.AddString(AddString.Response()),
        state => state.copy(result = state.result + value.value)
      )
    case Req.GetState(value) =>
      state.respond(Res.GetState(GetState.Response(state = Some(state.get))))
  end match
end handleRequest
