package de.htwg.se.minesweeper.rest
import de.htwg.se.minesweeper.util.IObserver
import de.htwg.se.minesweeper.controller.IController
import scala.concurrent.ExecutionContext

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._

class Rest(using controller: IController) extends IObserver {

    override def update: Unit = {}

    implicit val system: ActorSystem = ActorSystem()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContext = system.dispatcher

    val route =
        get {
            concat(
              path("state") {
                  complete(controller.getGrid.toString)
              },
              path("flag" / IntNumber / IntNumber) { (row: Int, col: Int) =>                  
                  complete(controller.flagCell(row, col).toString)
              },
              path("open" / IntNumber / IntNumber) { (row: Int, col: Int) =>
                  complete(controller.openCell(row, col).toString)
              },
              path("undo") {
                  complete(controller.undo().toString)
              },
              path("redo") {
                  complete(controller.redo().toString)
              },
              path("solve") {
                  complete(controller.openGrid.toString)
              },
              path("") {
                  sys.error("BOOM!")
              }
            )
        }

    // `route` will be implicitly converted to an async handler
    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

}

