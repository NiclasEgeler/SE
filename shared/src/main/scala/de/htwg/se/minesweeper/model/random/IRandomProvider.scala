package de.htwg.se.minesweeper.model.random

trait IRandomProvider {
    def between(min: Int, max: Int): Int
}