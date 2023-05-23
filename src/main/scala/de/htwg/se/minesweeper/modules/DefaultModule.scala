package de.htwg.se.minesweeper.modules

import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.controller._
import de.htwg.se.minesweeper.fileIO._
import de.htwg.se.minesweeper.generator._


object DefaultModule {
    given Difficulty = Difficulty.Easy
    given IDifficultyProvider = DifficultyProvider()
    given IRandomProvider = RandomProvider()
    given IGenerator = GeneratorRest()
    given IController = ControllerRest()
    // given IFileIO = MongoDAO()
}