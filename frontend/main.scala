import com.raquo.laminar.api.L.*
import org.scalajs.dom.document
import sttp.tapir._
import sttp.tapir.client.sttp.SttpClientInterpreter
import sttp.client3._
import scala.concurrent.Future

def getTodos(): Future[GetTodosResponse] = SttpClientInterpreter()
  .toQuickClientThrowErrors(endpoints.getTodos, baseUri = None)
  .apply(())

val todosSignal = Signal.fromFuture(getTodos())

def app = div(
  h1("Hello from Laminar!"),
  h2("Todos:"),
  child <-- todosSignal.map {
    case None => p("Waiting for response from server")
    case Some(todos) =>
      table(
        tr(th("id"), th("message"), th("done")),
          todos.data.map(todo => tr(td(todo.id.toString), td(todo.message), td(todo.done)))
      )
  }
)

@main
def main: Unit =
  renderOnDomContentLoaded(document.getElementById("app"), app)
