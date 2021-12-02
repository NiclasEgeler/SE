package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.generator._
import de.htwg.se.minesweeper.model.cell.CellFactory
import de.htwg.se.minesweeper.util._
import de.htwg.se.minesweeper.controller.commands._

class Controller(var generator: IGenerator) extends IController {
    val UndoManager = new UndoManager[Grid]
    var grid        = generator.generate()

    def flagCell(row: Int, column: Int): Option[Grid] = {
        if (!validateCoordinates(row, column))
            return None
        grid = grid.getCell(row, column).isHidden match {
            case false => grid
            case true  => UndoManager.doStep(grid, FlagCommand(row, column))
        }
        notifyObservers
        return Some(grid)
    }

    def openCell(row: Int, column: Int): Option[Grid] = {
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

    def undo(): Grid = {
        grid = UndoManager.undoStep(grid)
        notifyObservers
        return grid
    }

    def redo(): Grid = {
        grid = UndoManager.redoStep(grid)
        notifyObservers
        return grid
    }

    def openGrid: Grid = {
        grid = UndoManager.doStep(grid, OpenGridCommand())
        notifyObservers
        return grid
    }

    def validateCoordinates(x: Int, y: Int): Boolean = {
        // TODO: Validator?
        return (x >= 0 && y >= 0 && grid.getHeight > y && grid.getWidth > x)
    }

    def getGrid: Grid = grid
}
