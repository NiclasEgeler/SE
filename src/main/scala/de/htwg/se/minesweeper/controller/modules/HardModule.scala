package de.htwg.se.minesweeper.controller.modules

import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.model.generator._
import de.htwg.se.minesweeper.controller._
import de.htwg.se.minesweeper.model.fileIO._
object HardModule {
    given Difficulty = Difficulty.Hard
    given IDifficultyProvider = DifficultyProvider()
    given IRandomProvider = MockRandomProvider()
    given IGenerator = MineGridGenerator()
    given IFileIO = FileIOToXML()
    given IController = Controller()
}