package de.htwg.se.minesweeper.model.generator
import de.htwg.se.minesweeper.model.grid._

trait IGenerator {
    def generate(): IGrid;
}