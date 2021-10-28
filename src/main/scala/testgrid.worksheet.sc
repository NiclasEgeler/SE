println(grid(2, 2))

def top() = "┼" + "┤" + "└" + "┘" + "├" + "┐" + "┌" + "─" + "┴" + "┬"

def topLeft()   = "╭" + horizontalLine()
def topCenter() = "┬" + horizontalLine()
def topRight()  = "╮" + newLine()

def centerLeft()   = "├" + horizontalLine()
def centerCenter() = "┼" + horizontalLine()
def centerRight()  = "┤" + newLine()

def bottomLeft()   = "╰" + horizontalLine()
def bottomCenter() = "┴" + horizontalLine()
def bottomRight()  = "╯" + newLine()

def newLine() = "\n"

def horizontalLine()              = "─" * 3
def verticalLines(width: Int = 9) = ("│" + " " * 3) * (width + 1) + newLine()

def topRow(width: Int = 9) =
    topLeft() + topCenter() * (width - 1) + topRight() + verticalLines(width)
def centerRow(width: Int = 9) =
    centerLeft() + centerCenter() * (width - 1) + centerRight() + verticalLines(width)
def bottomRow(width: Int = 9) = bottomLeft() + bottomCenter() * (width - 1) + bottomRight()

def grid(width: Int = 9, height: Int = 9) =
    topRow(width) + centerRow(width) * (height - 1) + bottomRow(width)
