package de.htwg.se.minesweeper.controller
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.model.Grid

trait IController extends Observable{
    def openCell(row: Int, column: Int): Grid
    def flagCell(row: Int, column: Int): Grid
    def openGrid: Grid
    def getGrid: Grid
}   