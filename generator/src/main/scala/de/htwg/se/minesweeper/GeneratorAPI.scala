package de.htwg.se.minesweeper.generator
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import scala.concurrent.ExecutionContext
import de.htwg.se.minesweeper.generator.IGenerator

class GeneratorAPI(using generator: IGenerator) {

    implicit val system: ActorSystem = ActorSystem()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContext = system.dispatcher

    val route =
        get {
            concat(
              path("generate") {
                  complete(generator.generate().toString)
              }
            )
        }

    // `route` will be implicitly converted to an async handler
    val bindingFuture = Http().newServerAt("0.0.0.0", 8081).bind(route)
    println(s"Generator service online at http://0.0.0.0:8081/\nPress RETURN to stop...")
    while(true){}
}
