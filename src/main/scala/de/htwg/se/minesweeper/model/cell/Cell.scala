package de.htwg.se.minesweeper.model.cell

object CellFactory {
    def apply(name: String, value: Int) = name.toLowerCase match {
        case "hidden"  => new HiddenCell(value)
        case "open"    => new OpenCell(value)
        case "flagged" => new FlagCell(value)
    }
}

private abstract class Cell(value: Int) extends ICell {
    def isMine: Boolean    = value < 0
    def getValue: Int      = value
    def isHidden: Boolean  = true
    def isFlagged: Boolean = false
}

private case class HiddenCell(value: Int) extends Cell(value) {}

private case class OpenCell(value: Int) extends Cell(value) {
    override def isHidden: Boolean  = false
    override def isFlagged: Boolean = false
}
private case class FlagCell(value: Int) extends Cell(value) {
    override def isHidden: Boolean  = true
    override def isFlagged: Boolean = true
}
