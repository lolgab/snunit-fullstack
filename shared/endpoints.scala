import sttp.tapir.*
import sttp.tapir.json.upickle.*
import sttp.tapir.generic.auto.*
import upickle.default.*

case class Todo(id: Long, message: String, done: Boolean) derives ReadWriter
case class GetTodosResponse(data: Seq[Todo]) derives ReadWriter

object endpoints {
  private val baseEndpoint =
    endpoint.in("api")

  val getTodos =
    baseEndpoint
      .get
      .in("todos")
      .out(jsonBody[GetTodosResponse])
}
