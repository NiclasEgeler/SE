package de.htwg.se.minesweeper.views.tui

import de.htwg.se.minesweeper.model.Grid;

class Tui(var grid: Grid) {
    
    def test(): Unit = {
        grid.printGrid()
    }

}