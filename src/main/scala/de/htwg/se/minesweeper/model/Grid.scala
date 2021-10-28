package de.htwg.se.minesweeper.model

case class Grid(val rows: Int, val columns: Int, val mines: Int) {

    def this(difficulty: Difficulty) =
        this(difficulty.rows, difficulty.columns, difficulty.numMines)

    var grid: Vector[Vector[Cell]] = Vector.tabulate(rows, columns) { (row, col) => new Cell()}

    def getCell(row: Int, col: Int): Int = {
        return row + col
    }

    def getWidth(): Int = {
        return columns
    }

    def getHeight(): Int = {
        return rows
    }
}
