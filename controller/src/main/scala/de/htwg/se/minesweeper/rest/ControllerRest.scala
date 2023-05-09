package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.cell._

class ControllerRest extends IController {

    


    val baseUrl = "http://0.0.0.0:8080"
    def openCell(row: Int, column: Int): Option[IGrid] = {
        var res = scala.io.Source.fromURL(baseUrl + "/open/" + row + "/" + column).mkString
        notifyObservers
        new Grid(1, 1).fromString(res)

    }
    def flagCell(row: Int, column: Int): Option[IGrid] = {
        var res = scala.io.Source.fromURL(baseUrl + "/flag/" + row + "/" + column).mkString
        notifyObservers
        new Grid(1, 1).fromString(res)
    }
    def undo(): IGrid = {
        var res = scala.io.Source.fromURL(baseUrl + "/undo").mkString
        notifyObservers
        unpack(new Grid(1, 1).fromString(res))
    }
    def redo(): IGrid = {
        var res = scala.io.Source.fromURL(baseUrl + "/redo").mkString
        notifyObservers
        unpack(new Grid(1, 1).fromString(res))
    }
    def save: IGrid = {
        var res = scala.io.Source.fromURL(baseUrl + "/save").mkString
        notifyObservers
        unpack(new Grid(1, 1).fromString(res))
    }
    def load: IGrid = {
        var res = scala.io.Source.fromURL(baseUrl + "/load").mkString
        notifyObservers
        unpack(new Grid(1, 1).fromString(res))
    }
    def openGrid: IGrid = {
        var res = scala.io.Source.fromURL(baseUrl + "/openGrid").mkString
        notifyObservers
        unpack(new Grid(1, 1).fromString(res))
    }
    def getGrid: IGrid = {
        var res = scala.io.Source.fromURL(baseUrl + "/getGrid").mkString
        unpack(new Grid(1, 1).fromString(res))
    }
    def getMines: List[Option[ICell]] = {
        var res = scala.io.Source.fromURL(baseUrl + "/getMines").mkString
        notifyObservers
        List[Option[ICell]]()
    }

    def unpack(option: Option[IGrid]): IGrid = {
        option match {
            case None        => new Grid(1, 1)
            case Some(value) => value
        }
    }
}
