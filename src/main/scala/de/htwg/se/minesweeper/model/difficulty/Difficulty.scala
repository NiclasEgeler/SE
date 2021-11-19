package de.htwg.se.minesweeper.model


enum Difficulty(var rows: Int, var columns: Int, var numMines: Int):
    case Easy extends Difficulty(9, 9, 10)
    case Medium extends Difficulty(16, 16, 40)
    case Hard extends Difficulty(16, 30, 99)
end Difficulty