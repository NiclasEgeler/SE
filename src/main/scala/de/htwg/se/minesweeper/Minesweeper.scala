package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.views.tui._
import de.htwg.se.minesweeper.views.gui._
import de.htwg.se.minesweeper.controller._

import de.htwg.se.minesweeper.modules.DefaultModule.{given}

object Minesweeper {
    var gui = SwingGui()
    var tui = Tui()

    def main(args: Array[String]) = {
        println("Welcome to Minesweeper")
        summon[IController].notifyObservers
        tui.run()
    }
}