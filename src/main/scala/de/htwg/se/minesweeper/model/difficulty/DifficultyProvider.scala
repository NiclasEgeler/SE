package de.htwg.se.minesweeper.model.difficulty
import de.htwg.se.minesweeper.model.difficulty.Difficulty

class DifficultyProvider(val difficulty: Difficulty) extends IDifficultyProvider {
    def get: Difficulty = difficulty
}