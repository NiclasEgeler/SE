package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.views.tui._
import de.htwg.se.minesweeper.views.gui._
import de.htwg.se.minesweeper.controller._

import de.htwg.se.minesweeper.modules.DefaultModule.{given}
import de.htwg.se.minesweeper.rest.Rest

object Minesweeper {
    var gui = SwingGui()
    var tui = Tui()
    val restThread = new Thread {
        override def run(): Unit = {
            var rest = Rest()
        }
    }
    

    def main(args: Array[String]) = {
        println("Welcome to Minesweeper")
        summon[IController].notifyObservers
        restThread.start()
        tui.run()
        restThread.join()
    }
}