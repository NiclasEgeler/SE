package de.htwg.se.minesweeper.model.fileIO

import de.htwg.se.minesweeper.model.grid._

trait IFileIO() {
    def load: Option[IGrid]
    def save(grid: IGrid): Unit
}
