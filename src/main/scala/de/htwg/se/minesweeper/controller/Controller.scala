package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.generator._

class Controller(var generator: IGenerator) extends IController {

    var grid = generator.generate()
    def flagCell(row: Int, column: Int): Grid = {
        var cell = grid.getCell(row, column)
        if(!cell.isHidden) {
            notifyObservers
            return grid            
        }
        grid = grid.setCell(row, column, cell.setFlag(!cell.isFlagged))
        notifyObservers
        return grid
    }

    def openCell(row: Int, column: Int): Grid = {
        var ret = openCellP(row, column)
        notifyObservers
        return ret        
    }

    def openCellP(row: Int, column: Int): Grid = {

        var cell = grid.getCell(row, column)
        if(cell.isFlagged || !cell.isHidden) {
            return grid
        }
        // if (cell.isMine == true) {
        //     // todo: Notify of losing game and winning game
        // }

        grid = grid.setCell(row, column, cell.setHidden(false))

        if(cell.getValue != 0)
            return grid

        for (d <- Directions.values) {
            var x = d.x+row
            var y = d.y+column
            if(validateCoordinates(x, y))
                openCellP(x, y)
        }
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

    def validateCoordinates(x: Int, y: Int): Boolean = {
        // todo: Validator?
        return (x >= 0 && y >= 0 && grid.getHeight > y && grid.getWidth > x)
    }

    def getGrid: Grid = grid
}
