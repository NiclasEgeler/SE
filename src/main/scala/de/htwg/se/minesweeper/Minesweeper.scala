package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.views.tui._
import de.htwg.se.minesweeper.builder._
import de.htwg.se.minesweeper.views.gui._

object Minesweeper {

    var builder    = new ControllerBuilder()
    var controller = builder.difficulty("easy").random("mock").getResult

    var gui = new SwingGui(controller)
    var tui = new Tui(controller)

    def main(args: Array[String]) = {
        println("Welcome to Minesweeper")
        controller.notifyObservers
        tui.run()

    }
}
