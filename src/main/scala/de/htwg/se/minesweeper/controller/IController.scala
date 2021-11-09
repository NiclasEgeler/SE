package de.htwg.se.minesweeper.controller
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.model.Grid

trait IController extends Observable{
    def openCell(row: Int, column: Int): Unit
    def flagCell(row: Int, column: Int): Unit
    def openGrid: Unit
    def getGrid: Grid
}   