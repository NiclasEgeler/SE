package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.model.Grid;
import de.htwg.se.minesweeper.views.tui.Tui;
import de.htwg.se.minesweeper.model.Difficulty

object Minesweeper {
  var tui = new Tui(new Grid(3,2,1))
  

   def main(args: Array[String]) = {
    println("Welcome to Minesweeper")
    tui.test()
  }
}

// @main def minesweeper: Unit =
//     println("Minesweeper")


// val eol = sys.props("line.separator")
