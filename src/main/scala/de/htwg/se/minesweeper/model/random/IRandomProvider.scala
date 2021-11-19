package de.htwg.se.minesweeper.model

trait IRandomProvider {
    def between(min: Int, max: Int): Int
}