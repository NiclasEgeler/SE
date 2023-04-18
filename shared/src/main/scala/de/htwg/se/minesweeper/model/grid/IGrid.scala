package de.htwg.se.minesweeper.model.grid

import de.htwg.se.minesweeper.model.cell._
import scala.collection._

trait IGrid extends immutable.Iterable[ICell] {
    def setCell(row: Int, col: Int, cell: ICell): IGrid
    def getCell(row: Int, column: Int): ICell
    def iterator: Iterator[ICell]
    def getRow(row: Int): Vector[ICell]
    def getWidth: Int
    def getHeight: Int
    def toString: String
}