package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.generator._

// Todo: should get IGenerator
class Controller(var generator: IGenerator) extends IController {

    var grid = generator.generate()
    def flagCell(row: Int, column: Int): Grid = {
        var cell = grid.getCell(row, column)
        if(!cell.isHidden)
            return grid
        grid = grid.setCell(row, column, cell.setFlag(!cell.isFlagged))
        notifyObservers
        return grid
    }

    def openCell(row: Int, column: Int): Grid = {
        var cell = grid.getCell(row, column)
        if(cell.isFlagged)
            return grid
        // todo: If cell value is 0 check all directions open all cells around it
        grid = grid.setCell(row, column, cell.setHidden(false))
        if (cell.isMine == true) {
            // todo: Notify of losing game and winning game
        }
        notifyObservers
        return grid
    }

    def openGrid: Grid = {
        for (c <- 0 until grid.getWidth)
            for (r <- 0 until grid.getHeight)
                var cell = grid.getCell(r, c)
                grid = grid.setCell(r, c, cell.setHidden(false))
        notifyObservers
        return grid
    }

    def getGrid: Grid = grid
}
