package de.htwg.se.minesweeper.controller.commands
import de.htwg.se.minesweeper.util._

import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.cell._

case class FlagCommand(row: Int, column: Int) extends Command[Grid] {
    override def doStep(grid: Grid): Grid   = flagCell(grid)
    override def undoStep(grid: Grid): Grid = flagCell(grid)
    override def redoStep(grid: Grid): Grid = flagCell(grid)

    private def flagCell(grid: Grid): Grid = {
        var cell = grid.getCell(row, column)
        return cell.isHidden match {
            case false => grid
            case true =>
                grid.setCell(
                  row,
                  column,
                  if cell.isFlagged then CellFactory("hidden", cell.getValue)
                  else CellFactory("flagged", cell.getValue)
                )
        }
    }
}
