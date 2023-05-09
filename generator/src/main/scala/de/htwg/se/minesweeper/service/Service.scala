package de.htwg.se.minesweeper.generator

import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.random._

given Difficulty          = Difficulty.Easy
given IDifficultyProvider = DifficultyProvider()
given IRandomProvider     = RandomProvider()
given IGenerator          = MineGridGenerator()

object Minesweeper {
    def main(args: Array[String]) = {
        var rest = GeneratorAPI()
    }
}
