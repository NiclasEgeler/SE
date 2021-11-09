package de.htwg.se.minesweeper.model

case class Cell(val value: Int, val flag: Boolean, val hidden: Boolean) {

    def this(value: Int) = this(value, false, true)

    def isMine: Boolean    = value < 0
    def isHidden: Boolean  = hidden
    def isFlagged: Boolean = flag

    //  def setValue(value: Int): Cell       = copy(value, flag, hidden)
    def setHidden(hidden: Boolean): Cell = copy(value, flag, hidden)
    def setFlag(flag: Boolean): Cell     = copy(value, flag, hidden)
}
