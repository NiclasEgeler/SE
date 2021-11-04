package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.util.MineGenerator

case class Grid(grid: Vector[Vector[Cell]]) {
    def this(rows: Int, columns: Int, mines: Int) =
        this(MineGenerator.generate(rows, columns, mines))

    def this(difficulty: Difficulty) =
        this(difficulty.rows, difficulty.columns, difficulty.numMines)

    def setCell(row: Int, col: Int, cell: Cell): Grid = {
        return copy(grid.updated(row, grid(row).updated(col, cell)))
    }

    def getCell(row: Int, col: Int): Cell = {
        return grid(row)(col)
    }

    def getWidth(): Int = {
        return grid(0).size
    }

    def getHeight(): Int = {
        return grid.size
    }
}
