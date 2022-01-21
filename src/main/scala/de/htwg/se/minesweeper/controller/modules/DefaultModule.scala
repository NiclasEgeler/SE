package de.htwg.se.minesweeper.controller.modules

import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.model.generator._
import de.htwg.se.minesweeper.controller._
import de.htwg.se.minesweeper.model.fileIO._

object DefaultModule {
    given Difficulty = Difficulty.Easy
    given IDifficultyProvider = DifficultyProvider()
    given IRandomProvider = RandomProvider()
    given IGenerator = MineGridGenerator()
    given IController = Controller()
    given IFileIO = FileIOToXML()
}