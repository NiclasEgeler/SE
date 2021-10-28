package de.htwg.se.minesweeper.views.tui

import de.htwg.se.minesweeper.model.Grid;

class Tui(var grid: Grid) {
    val eol = sys.props("line.separator")
    def test(): Unit = {
        printGrid()
    }

    def printGrid(): Unit = {
        println(
          grid(grid.getWidth(), grid.getHeight())
        )
    }

    def topLeft()   = "╭" + horizontalLine()
    def topCenter() = "┬" + horizontalLine()
    def topRight()  = "╮" + eol

    def centerLeft()   = "├" + horizontalLine()
    def centerCenter() = "┼" + horizontalLine()
    def centerRight()  = "┤" + eol

    def bottomLeft()   = "╰" + horizontalLine()
    def bottomCenter() = "┴" + horizontalLine()
    def bottomRight()  = "╯" + eol

    def horizontalLine()              = "─" * 3
    def verticalLines(width: Int = 9) = ("│" + " " * 3) * width + "│" + eol

    def grid(width: Int = 9, height: Int = 9) =
        topBar(width) + verticalLines(width) + centerRow(width) * (height - 1) + bottomBar(width)
    def centerBar(width: Int = 9) =
        centerLeft() + centerCenter() * (width - 1) + centerRight()
    def topBar(width: Int = 9) =
        topLeft() + topCenter() * (width - 1) + topRight()
    def centerRow(width: Int = 9) =
        centerLeft() + centerCenter() * (width - 1) + centerRight() + verticalLines(width)
    def bottomBar(width: Int = 9) = bottomLeft() + bottomCenter() * (width - 1) + bottomRight()

}
