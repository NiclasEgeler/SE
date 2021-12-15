package de.htwg.se.minesweeper.builder

import de.htwg.se.minesweeper.controller._

trait IBuilder {
    def difficulty(name: String): IBuilder
    def random(name: String): IBuilder
    def getResult: IController
}
