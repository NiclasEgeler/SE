package de.htwg.se.minesweeper.generator

import de.htwg.se.minesweeper.generator.IGenerator
import de.htwg.se.minesweeper.model.grid._

class GeneratorRest extends IGenerator {
    def generate(): IGrid = {
        
        var res = scala.io.Source.fromURL("http://generator:8081/generate").mkString
        new Grid(1,1).fromString(res) match
            case Some(value) => value
            case None        => new Grid(1,1)
    }
}
