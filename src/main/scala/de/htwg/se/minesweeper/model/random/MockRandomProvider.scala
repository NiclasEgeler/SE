package de.htwg.se.minesweeper.model.random
import scala.util.Random

class MockRandomProvider extends IRandomProvider {
    val random = new Random(1337)
    def between(min: Int, max: Int): Int = {
        return random.between(min, max);
    }
}