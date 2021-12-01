package de.htwg.se.minesweeper.controller.commands

import de.htwg.se.minesweeper.util._
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.cell._

case class OpenCommand(row: Int, column: Int) extends Command[Grid] {
    var steps: List[Command[Grid]] = List()

    override def doStep(grid: Grid): Grid = {
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
        }
        return steps.foldLeft(newGrid) { (a, b) => b.doStep(a) }
    }

    override def undoStep(grid: Grid): Grid = steps.foldLeft(
      grid.setCell(row, column, CellFactory("hidden", grid.getCell(row, column).getValue))
    ) { (a, b) => b.undoStep(a) }

    override def redoStep(grid: Grid): Grid = doStep(grid)

    def validateCoordinates(x: Int, y: Int, grid: Grid): Boolean = {
        return (x >= 0 && y >= 0 && grid.getHeight > y && grid.getWidth > x)
    }
}
