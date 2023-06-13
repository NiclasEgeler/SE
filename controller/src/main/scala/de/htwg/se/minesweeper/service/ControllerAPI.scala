package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.util.IObserver
import de.htwg.se.minesweeper.controller.IController
import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn
import de.htwg.se.minesweeper.model.grid.Grid

class ControllerApi(using controller: IController) extends IObserver {

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
                  var grid = controller.flagCell(row, col) match
                      case Some(value) => value
                      case None => new Grid(1, 1)
                  complete(grid.toString)
              },
              path("open" / IntNumber / IntNumber) { (row: Int, col: Int) =>
                  var grid = controller.openCell(row, col) match
                      case Some(value) => value
                      case None => new Grid(1, 1)
                  complete(grid.toString)

              },
              path("undo") {
                  complete(controller.undo().toString)
              },
              path("redo") {
                  complete(controller.redo().toString)
              },
              path("openGrid") {
                  complete(controller.openGrid.toString)
              },
              path("save") {
                  controller.save
                  complete("")
              },
              path("load") {
                  complete(controller.load.toString)
              },
              path("getGrid") {
                  complete(controller.getGrid.toString)
              },
              path("getMines") {
                  complete(controller.getMines.toString)
              },
              path("") {
                  sys.error("BOOM!")
              }
            )
        }

    // `route` will be implicitly converted to an async handler
    val bindingFuture = Http().newServerAt("0.0.0.0", 8080).bind(route)
    println(s"Rest service online at http://0.0.0.0:8080/\nPress RETURN to stop...")
    while (true) {}
}
