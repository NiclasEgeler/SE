package de.htwg.se.minesweeper
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
    // val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
    // println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
}
