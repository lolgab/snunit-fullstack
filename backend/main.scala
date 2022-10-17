import snunit.*
import snunit.tapir.SNUnitIdServerInterpreter.*

@main
def main: Unit =
  SyncServerBuilder
    .build(toHandler(serverEndpoints.list))
    .listen()
