package de.htwg.se.minesweeper.builder

import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.controller._
import de.htwg.se.minesweeper.model.generator._

trait IBuilder {
    def difficulty(name: String): IBuilder
    def random(name: String): IBuilder
    def getResult: IController
}

class ControllerBuilder extends IBuilder {
    var diff: IDifficultyProvider = _
    var rand: IRandomProvider = _
    
    def difficulty(name: String): IBuilder = {
        name match {
            case "easy" => diff = new DifficultyProvider(Difficulty.Easy)
            case "medium" => diff = new DifficultyProvider(Difficulty.Medium)
            case "hard" => diff = new DifficultyProvider(Difficulty.Hard)
        }
        return this
    }

    def random(name: String): IBuilder = {
        name match {
            case "random" => rand = new RandomProvider()
            case "mock" => rand = new MockRandomProvider()
        }
        return this
    } 

    def getResult: IController = {
        return new Controller(new MineGridGenerator(rand, diff))
    }
}