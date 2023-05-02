package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.generator._
import de.htwg.se.minesweeper.model.cell.CellFactory
import de.htwg.se.minesweeper.util._
import de.htwg.se.minesweeper.controller.commands._
import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.fileIO._
import de.htwg.se.minesweeper.model.cell.ICell
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.model._
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext

class Controller(using generator: IGenerator)(using fileIO: IFileIO) extends IController {
    val UndoManager                          = new UndoManager[IGrid]
    implicit val system: ActorSystem[String] = ActorSystem(Behaviors.empty[String], "SingleRequest")
    implicit val executionContext: ExecutionContext = system.executionContext
    val responseFuture: Future[HttpResponse] =
        Http().singleRequest(HttpRequest(uri = "http://0.0.0.0:8081/generate"))

    var grid: IGrid = new Grid(1, 1)
    responseFuture.onComplete {
        case Success(res) =>
            grid.fromString(res.toString) match {
                case None        => sys.error("error")
                case Some(value) => grid = value
            }
        case Failure(_) => sys.error("something wrong")
    }

    def flagCell(row: Int, column: Int): Option[IGrid] = {
        if (!validateCoordinates(row, column))
            return None
        grid = grid.getCell(row, column).isHidden match {
            case false => grid
            case true  => UndoManager.doStep(grid, FlagCommand(row, column))
        }
        notifyObservers
        return Some(grid)
    }

    def openCell(row: Int, column: Int): Option[IGrid] = {
        if (!validateCoordinates(row, column))
            return None
        var cell = grid.getCell(row, column)
        if (cell.isFlagged || !cell.isHidden) {
            return None
        }
        grid = UndoManager.doStep(grid, OpenCommand(row, column))
        notifyObservers
        return Some(grid)
    }

    def undo(): IGrid = {
        grid = UndoManager.undoStep(grid)
        notifyObservers
        return grid
    }

    def redo(): IGrid = {
        grid = UndoManager.redoStep(grid)
        notifyObservers
        return grid
    }

    def load: IGrid = {
        var result = fileIO.load
        result match {
            case Some(v: IGrid) => this.grid = v
            case None           =>
        }
        notifyObservers
        return grid;
    }

    def save: IGrid = {
        fileIO.save(grid)
        return grid;
    }

    def openGrid: IGrid = {
        grid = UndoManager.doStep(grid, OpenGridCommand())
        notifyObservers
        return grid
    }

    def validateCoordinates(x: Int, y: Int): Boolean = {
        return (x >= 0 && y >= 0 && grid.getHeight > y && grid.getWidth > x)
    }

    def getMines: List[Option[ICell]] =
        (for (cell <- grid) yield { if cell.isMine then Some(cell) else None }).toList

    def getGrid: IGrid = grid
}
