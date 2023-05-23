package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.fileIO.IFileIO
import de.htwg.se.minesweeper.model.grid._

class FileIORest extends IFileIO {
    def load(id: Int): Option[IGrid] = {
        val res = scala.io.Source.fromURL("http://0.0.0.0:8082/load").mkString
        new Grid(1, 1).fromString(res)
    }
    def save(grid: IGrid): Unit = {
        // TODO: mit daten shit senden
    }
}
