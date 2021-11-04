package de.htwg.se.minesweeper.util
import de.htwg.se.minesweeper.model.Cell

object MineGenerator {
    def generate(rows: Int, columns: Int, mines: Int): Vector[Vector[Cell]] = {
        return Vector.tabulate(rows, columns) { (row, col) => new Cell(row) }
    }
}
