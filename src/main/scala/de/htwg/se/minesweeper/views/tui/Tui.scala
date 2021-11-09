package de.htwg.se.minesweeper.views.tui

import de.htwg.se.minesweeper.model.Grid;
import de.htwg.se.minesweeper.model.Cell;
import scala.io.StdIn.readLine
import scala.util.matching.Regex
import de.htwg.se.minesweeper.util.IObserver
import de.htwg.se.minesweeper.controller.IController

class Tui(var controller: IController) extends IObserver {

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
                    case s"o $x $y"   => controller.openCell(x.toInt - 1, y.toInt - 1)
                    case "open"       => controller.openGrid
                    case s"f $x $y"   => controller.flagCell(x.toInt - 1, y.toInt - 1)
                    case _            => println("YIKES DOG")
                run()
            }

    }

    // def openCell(x: Int, y: Int) =
    //     // grid.setCell(x, y, grid.getCell(x, y).setHidden(false))

    // def flagCell(x: Int, y: Int) = {

    //     // var cell = grid.getCell(x.toInt, y.toInt);
    //     // grid.setCell(x, y, cell.setFlag(!cell.isFlagged))
    // }

    def printCell(cell: Cell): String = {
        if (cell.isFlagged) {
            return "⚑"
        }
        if (cell.isHidden) {
            return "?"
        }
        if (cell.isMine)
            return "#"
        return if (cell.value > 0) then cell.value.toString() else " "
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
    // def verticalLines(width: Int = 9) = ("│" + " " * 3) * width + "│" + eol
    def verticalLines(grid: Grid, row: Int = 0): String = {
        var vLine: String = ""
        for (a <- 0 until grid.getWidth) {
            vLine = vLine + "│ " + printCell(grid.getCell(row, a)) + " "
        }
        return vLine + "│ " + (row + 1) + eol;
    }
    def xAxis(width: Int = 9): String = {
        var axis: String = "  "
        for (a <- 1 to width) {
            axis = axis + a + (" " * 3)
        }
        axis = axis + eol
        return axis
    }
    def grid(grid: Grid): String = {
        var width  = grid.getWidth
        var height = grid.getHeight

        return xAxis(width)
            + topBar(width)
            + (for (i: Int <- 0 until width)
                yield valueBar(i, width, grid))
                .foldLeft("") { (b, a) => b + a }
            + bottomBar(width)
    }

    def valueBar(row: Int, width: Int, grid: Grid): String =
        if row == 0 then verticalLines(grid, 0) else centerRow(grid, row)

    // def grid(width: Int = 9, height: Int = 9) =
    //     xAxis(width) + topBar(width) + verticalLines(width) + centerRow(width) * (height - 1) + bottomBar(width)
    def centerBar(width: Int = 9) =
        centerLeft() + centerCenter() * (width - 1) + centerRight()
    def topBar(width: Int = 9) =
        topLeft() + topCenter() * (width - 1) + topRight()
    def centerRow(grid: Grid, row: Int) =
        centerLeft() + centerCenter() * (grid.getWidth - 1) + centerRight() + verticalLines(
          grid,
          row
        )
    def bottomBar(width: Int = 9) = bottomLeft() + bottomCenter() * (width - 1) + bottomRight()

}
