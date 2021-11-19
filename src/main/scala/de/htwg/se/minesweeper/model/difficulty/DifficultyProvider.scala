package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.model._

class DifficultyProvider(val difficulty: Difficulty) extends IDifficultyProvider {
    
    def get: Difficulty = difficulty; 
}