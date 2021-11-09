package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.views.tui.Tui
import de.htwg.se.minesweeper.model.Difficulty
import de.htwg.se.minesweeper.model.MineGridGenerator
import de.htwg.se.minesweeper.controller.Controller


object Minesweeper {
  
  var controller = new Controller(MineGridGenerator.generate(Difficulty.Easy))
  var tui = new Tui(controller)
  

   def main(args: Array[String]) = {
    println("Welcome to Minesweeper")
    controller.notifyObservers
    tui.run()
  }
}

// @main def minesweeper: Unit =
//     println("Minesweeper")


// val eol = sys.props("line.separator")
