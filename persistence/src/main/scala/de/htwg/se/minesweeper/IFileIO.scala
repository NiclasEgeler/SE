package de.htwg.se.minesweeper.fileIO

import de.htwg.se.minesweeper.model.grid._

trait IFileIO() {
    def load(id: Int): Option[IGrid]
    def save(grid: IGrid): Unit
}
