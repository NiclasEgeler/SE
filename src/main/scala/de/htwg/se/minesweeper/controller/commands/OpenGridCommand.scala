package de.htwg.se.minesweeper.controller.commands

import de.htwg.se.minesweeper.util._
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.cell._

case class OpenGridCommand() extends ICommand[IGrid] {
    var steps: List[(Int, Int, ICell)] = List()

    override def doStep(grid: IGrid): IGrid = {
        var newGrid = grid
        for (c <- 0 until grid.getWidth)
            for (r <- 0 until grid.getHeight) {
                var cell = grid.getCell(r, c)
                if (cell.isHidden)
                    steps = (r, c, cell) :: steps
            }
        return steps.foldLeft(grid) { (a, b) =>
            a.setCell(b._1, b._2, CellFactory("open", b._3.getValue))
        }
    }

    override def undoStep(grid: IGrid): IGrid = {
        steps.foldLeft(grid) { (a, b) =>
            a.setCell(b._1, b._2, b._3)
        }
    }

    override def redoStep(grid: IGrid): IGrid = doStep(grid)

}
