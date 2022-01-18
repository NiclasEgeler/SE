package de.htwg.se.minesweeper.views.tui

import de.htwg.se.minesweeper.model.grid._;
import de.htwg.se.minesweeper.model.cell._;
import scala.io.StdIn.readLine
import scala.util.matching.Regex
import de.htwg.se.minesweeper.util.IObserver
import de.htwg.se.minesweeper.controller.IController

class Tui(using controller: IController) extends IObserver {

    val eol = sys.props("line.separator")
    controller.add(this)

    override def update: Unit = {
        println(grid(controller.getGrid))
    }

    def run(): Unit = {
        val input = readLine()
        input match
            case "q" | "quit" =>
            case _ => {
                input match
                    case "h" | "help" => println("HELP")
                    case s"o $x $y" =>
                        controller.openCell(x.toInt - 1, y.toInt - 1) match {
                            case None       => println("Invalid Operation")
                            case Some(grid) => println(s"Opened cell ${x} ${y}")
                        }
                    case "open" => controller.openGrid
                    case s"f $x $y" =>
                        controller.flagCell(x.toInt - 1, y.toInt - 1) match {
                            case None       => println("Invalid Operation")
                            case Some(grid) => println(s"Flagged cell ${x} ${y}")
                        }

                    case "u" => controller.undo()
                    case "r" => controller.redo()
                    case "s" => controller.save
                    case "l" => controller.load
                    case _   => println(s"Invalid operation ${input}!")
                run()
            }
    }
    def printCell(cell: ICell): String = {
        if (cell.isFlagged) {
            return "⚑"
        }
        if (cell.isHidden) {
            return "?"
        }
        if (cell.isMine)
            return "#"
        return if (cell.getValue > 0) then cell.getValue.toString() else " "
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

    def horizontalLine() = "─" * 3

    def verticalLines(grid: IGrid, row: Int = 0): String = {
        var vLine: String = ""
        for (a <- 0 until grid.getWidth) {
            vLine = vLine + "│ " + printCell(grid.getCell(row, a)) + " "
        }
        return vLine + "│ " + (row + 1) + eol;
    }

    def xAxis(width: Int = 9): String = {
        var axis: String = "  "
        for (a <- 1 to width) {
            axis = axis + a + (" " * (3 - (s"$a").length + 1))
        }
        axis = axis + eol
        return axis
    }
    def grid(grid: IGrid): String = {
        var width  = grid.getWidth
        var height = grid.getHeight

        return xAxis(width)
            + topBar(width)
            + (for (i: Int <- 0 until height)
                yield valueBar(i, width, grid))
                .foldLeft("") { (b, a) => b + a }
            + bottomBar(width)
    }

    def valueBar(row: Int, width: Int, grid: IGrid): String =
        if row == 0 then verticalLines(grid, 0) else centerRow(grid, row)

    def centerBar(width: Int = 9) =
        centerLeft() + centerCenter() * (width - 1) + centerRight()
    def topBar(width: Int = 9) =
        topLeft() + topCenter() * (width - 1) + topRight()
    def centerRow(grid: IGrid, row: Int) =
        centerLeft() + centerCenter() * (grid.getWidth - 1) + centerRight() + verticalLines(
          grid,
          row
        )
    def bottomBar(width: Int = 9) = bottomLeft() + bottomCenter() * (width - 1) + bottomRight()

}
