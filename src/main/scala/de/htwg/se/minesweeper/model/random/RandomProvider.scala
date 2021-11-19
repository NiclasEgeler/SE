package de.htwg.se.minesweeper.model
import scala.util.Random

class RandomProvider extends IRandomProvider {
    val random = new Random()
    def between(min: Int, max: Int): Int = {
        return random.between(min, max);
    }
}