package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.generator._
import de.htwg.se.minesweeper.fileIO._

given Difficulty          = Difficulty.Easy
given IDifficultyProvider = DifficultyProvider()
given IRandomProvider     = RandomProvider()
given IGenerator          = GeneratorRest()
given IFileIO             = FileSlick()
given IController         = Controller()

object Minesweeper {
    def main(args: Array[String]) = {
        var rest = ControllerApi()
    }
}
