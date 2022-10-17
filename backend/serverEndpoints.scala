import snunit.tapir.SNUnitIdServerInterpreter.Id

object serverEndpoints:
  private val getTodosImpl =
    endpoints.getTodos.serverLogicSuccess[Id](_ =>
      GetTodosResponse(Seq(Todo(1, "Something to do", done = false)))
    )

  val list = getTodosImpl :: Nil
