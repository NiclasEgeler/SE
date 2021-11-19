package de.htwg.se.minesweeper.model.generator
import de.htwg.se.minesweeper.model._

trait IGenerator {
    def generate(): Grid;
}