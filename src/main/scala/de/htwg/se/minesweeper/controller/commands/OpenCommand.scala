package de.htwg.se.minesweeper.controller.commands

import de.htwg.se.minesweeper.util._
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.cell._

case class OpenCommand(row: Int, column: Int) extends ICommand[IGrid] {
    var steps: List[ICommand[IGrid]] = List()

    override def doStep(grid: IGrid): IGrid = {
        var cell    = grid.getCell(row, column)
        var newGrid = grid.setCell(row, column, CellFactory("open", cell.getValue))

        if (cell.getValue != 0)
            return newGrid

        for (d <- Directions.values) {
            var x = d.x + row
            var y = d.y + column
            if (validateCoordinates(x, y, newGrid)) {
                var cell = newGrid.getCell(x, y)
                if (!cell.isFlagged && cell.isHidden)
                    steps = OpenCommand(x, y) :: steps
            }
        } // TODO: Hier higher order functions chainen?
        return steps.foldLeft(newGrid) { (a, b) => b.doStep(a) }
    }

    override def undoStep(grid: IGrid): IGrid = steps.foldLeft(
      grid.setCell(row, column, CellFactory("hidden", grid.getCell(row, column).getValue))
    ) { (a, b) => b.undoStep(a) }

    override def redoStep(grid: IGrid): IGrid = doStep(grid)

    def validateCoordinates(x: Int, y: Int, grid: IGrid): Boolean = {
        return (x >= 0 && y >= 0 && grid.getHeight > y && grid.getWidth > x)
    }
}
