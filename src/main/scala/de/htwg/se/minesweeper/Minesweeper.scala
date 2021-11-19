package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.views.tui._
import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.generator._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.controller._


object Minesweeper {
  
  var generator = new MineGridGenerator(new RandomProvider(),new DifficultyProvider(Difficulty.Easy));
  var controller = new Controller(generator)
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
