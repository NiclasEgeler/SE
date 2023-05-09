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
    val UndoManager = new UndoManager[IGrid]
    var grid: IGrid = new Grid(1, 1)
    val res: String = scala.io.Source.fromURL("http://0.0.0.0:8081/generate").mkString

    println(res)
    grid.fromString(res) match
        case Some(value) => grid = value
        case None        => grid = generator.generate()

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
