package de.htwg.se.minesweeper.model

case class Grid(grid: Vector[Vector[Cell]]) {
    def this(rows: Int, columns: Int) =
        this(Vector.tabulate(rows, columns) { (row, col) => new Cell(0) })

    // def this(difficulty: Difficulty) =
    //     this(difficulty.rows, difficulty.columns, difficulty.numMines)

    def setCell(row: Int, col: Int, cell: Cell): Grid = {
        return copy(grid.updated(row, grid(row).updated(col, cell)))
    }

    def getCell(row: Int, col: Int): Cell = {
        return grid(row)(col)
    }

    def getRow(row: Int): Vector[Cell] = grid(row)

    def getWidth: Int = grid(0).size 

    def getHeight: Int = grid.size
}
