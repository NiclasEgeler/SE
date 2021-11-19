package de.htwg.se.minesweeper.model.difficulty
import de.htwg.se.minesweeper.model.difficulty.Difficulty

trait IDifficultyProvider {
    def get: Difficulty;
}