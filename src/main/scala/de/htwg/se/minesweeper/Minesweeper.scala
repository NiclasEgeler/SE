package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.model.Grid;
import de.htwg.se.minesweeper.views.tui.Tui;

object Minesweeper2 {
  var tui = new Tui(new Grid())
  

   def main(args: Array[String]) = {
    println("Hello world")
    tui.test()
  }
}

// @main def minesweeper: Unit =
//     println("Minesweeper")


// val eol = sys.props("line.separator")
