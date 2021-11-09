package de.htwg.se.minesweeper.model

enum Directions(var x: Int, var y: Int):
    case UpLeft extends Directions(-1, -1)
    case Up extends Directions(0, -1)
    case UpRight extends Directions(1, -1)
    case Right extends Directions(1, 0)
    case DownRight extends Directions(1, 1)
    case Down extends Directions(0, 1)
    case DownLeft extends Directions(-1, 1)
    case Left extends Directions(-1, 0)
end Directions
