package de.htwg.se.minesweeper.model
import scala.collection._
import de.htwg.se.minesweeper.model.cell._

case class Grid(grid: Vector[Vector[ICell]]) extends immutable.Iterable[ICell] {

    def this(rows: Int, columns: Int) =
        this(Vector.tabulate(rows, columns) { (row, col) => CellFactory("hidden", 0) })

    def setCell(row: Int, col: Int, cell: ICell): Grid = {
        return copy(grid.updated(row, grid(row).updated(col, cell)))
    }

    def getCell(row: Int, col: Int): ICell = {
        return grid(row)(col)
    }

    def iterator: Iterator[ICell] = new AbstractIterator[ICell] {
        private var row = 0
        private var col = 0

        def hasNext = row < getHeight
        def next(): ICell = {
            var cell = getCell(row, col)
            col += 1
            if (col >= getWidth) {
                col = 0
                row += 1
            }
            return cell
        }
    }

    def getRow(row: Int): Vector[ICell] = grid(row)

    def getWidth: Int = grid(0).size

    def getHeight: Int = grid.size

}
