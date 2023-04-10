package de.htwg.se.minesweeper.controller.commands
import de.htwg.se.minesweeper.util._

import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.cell._

case class FlagCommand(row: Int, column: Int) extends ICommand[IGrid] {
    override def doStep(grid: IGrid): IGrid   = flagCell(grid)
    override def undoStep(grid: IGrid): IGrid = flagCell(grid)
    override def redoStep(grid: IGrid): IGrid = flagCell(grid)

    private def flagCell(grid: IGrid): IGrid = {
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
