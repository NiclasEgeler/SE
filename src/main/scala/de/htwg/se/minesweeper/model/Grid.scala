package de.htwg.se.minesweeper.model

class Grid(var rows: Int = 5, var columns: Int = 5, var numMines: Int = 5) {
    

    val EOL = sys.props("line.separator")

    def top() = "┼" + "┤" + "└" + "┘" + "├" + "┐" + "┌" + "─" + "┴" + "┬"

    def topLeft() = "╭" + horizontalLine()
    def topCenter() = "┬" + horizontalLine()
    def topRight() = "╮" + EOL

    def centerLeft() = "├" + horizontalLine()
    def centerCenter() = "┼" + horizontalLine()
    def centerRight() = "┤" + EOL

    def bottomLeft() = "╰" + horizontalLine()
    def bottomCenter() = "┴" + horizontalLine()
    def bottomRight() = "╯" + EOL

    def horizontalLine() = "─" * 3
    def verticalLines(width: Int = 9) = ("│" + " " * 3) * (width + 1) + EOL

    def topRow(width: Int = 9) = topLeft() +  topCenter() * (width - 1) + topRight() + verticalLines(width)
    def centerRow(width: Int = 9) = centerLeft() + centerCenter() * (width - 1) + centerRight() + verticalLines(width)
    def bottomRow(width: Int = 9) = bottomLeft() + bottomCenter() * (width - 1) + bottomRight()


    def grid(width: Int = 9, height: Int = 9) = topRow(width) + centerRow(width) * (height - 1) + bottomRow(width)
    
    def printGrid(): Unit =
        println(grid(6, 6))
    
    println("constructer ended")

}