package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.model.Grid;
import de.htwg.se.minesweeper.views.tui.Tui;
import de.htwg.se.minesweeper.model.Difficulty

object Minesweeper {
  var tui = new Tui(new Grid(Difficulty.Easy))
  

   def main(args: Array[String]) = {
    println("Welcome to Minesweeper")
    tui.run()
  }
}

// @main def minesweeper: Unit =
//     println("Minesweeper")


// val eol = sys.props("line.separator")
