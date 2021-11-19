package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.model._

trait IGenerator {
    def generate(): Grid;
}