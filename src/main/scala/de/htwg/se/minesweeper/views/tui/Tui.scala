package de.htwg.se.minesweeper.views.tui

import de.htwg.se.minesweeper.model.Grid;
import de.htwg.se.minesweeper.model.Cell;
import scala.io.StdIn.readLine
import scala.util.matching.Regex

class Tui(var grid: Grid) {
    val eol            = sys.props("line.separator")
    val pattern: Regex = "([mfudlr]) ([0-9]*) ([0-9]*)".r
    def printGrid(): Unit = {
        println(
          grid(grid.getWidth(), grid.getHeight())
        )
    }

    def parseInput(input: String): Unit = {}

    def run(): Unit = {
        // print("\u001b[2J")
        printGrid()
        //grid.setCell(1, 0, 2)
        val input = readLine()
        input match
            case "q" | "quit" =>
            case _ => {
                input match
                    case "h" | "help" => println("HELP")
                    case s"o $x $y"   => grid = openCell(x.toInt - 1, y.toInt - 1)
                    case s"f $x $y"   => grid = flagCell(x.toInt - 1, y.toInt - 1)
                    case _            => println("YIKES DOG")
                println(grid)
                run()
            }

    }

    def openCell(x: Int, y: Int): Grid =
        grid.setCell(x, y, grid.getCell(x, y).setHidden(false))

    def flagCell(x: Int, y: Int): Grid = {
        var cell = grid.getCell(x.toInt, y.toInt);
        grid.setCell(x, y, cell.setFlag(!cell.isFlagged))
    }

    def printCell(cell: Cell): String = {
        if (cell.isFlagged) {
            return "⚑"
        }
        if (cell.isHidden) {
            return "?"
        }
        return if (cell.value > 0) cell.value.toString() else " "
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
    def verticalLines(width: Int = 9, row: Int=0): String = {
        var vLine: String = ""
        for (a <- 0 until width) {
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
    def grid(width: Int = 9, height: Int = 9): String = {
        var gridString = ""
        gridString += xAxis(width) + topBar(width)
        for (a <- 0 until height) {
            if (a == 0) {
                gridString = gridString + verticalLines(width, 0)
            } else {
                gridString = gridString + centerRow(width, a)
            }
        }
        return gridString + bottomBar(width)
    }

    // def grid(width: Int = 9, height: Int = 9) =
    //     xAxis(width) + topBar(width) + verticalLines(width) + centerRow(width) * (height - 1) + bottomBar(width)
    def centerBar(width: Int = 9) =
        centerLeft() + centerCenter() * (width - 1) + centerRight()
    def topBar(width: Int = 9) =
        topLeft() + topCenter() * (width - 1) + topRight()
    def centerRow(width: Int = 9, row: Int) =
        centerLeft() + centerCenter() * (width - 1) + centerRight() + verticalLines(width, row)
    def bottomBar(width: Int = 9) = bottomLeft() + bottomCenter() * (width - 1) + bottomRight()

}
