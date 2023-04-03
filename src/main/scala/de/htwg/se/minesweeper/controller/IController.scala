package de.htwg.se.minesweeper.controller
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.cell.ICell

trait IController extends Observable {
    def openCell(row: Int, column: Int): Option[IGrid]
    def flagCell(row: Int, column: Int): Option[IGrid]
    def undo(): IGrid
    def redo(): IGrid
    def save: IGrid
    def load: IGrid
    def openGrid: IGrid
    def getGrid: IGrid
    def getMines: List[Option[ICell]]
}
