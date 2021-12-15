package de.htwg.se.minesweeper.model.cell

trait ICell {
    def isMine: Boolean
    def getValue: Int
    def isHidden: Boolean
    def isFlagged: Boolean
}
