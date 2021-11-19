package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.model._

trait IDifficultyProvider {
    def get: Difficulty;
}