package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.cell._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

class ControllerRest extends IController {

    val baseUrl = "http://0.0.0.0:8080"

    def openCell(row: Int, column: Int): Option[IGrid] = {
        val res = fromUrl(baseUrl + "/open/" + row + "/" + column)
        Await.result(
          res.map(value => new Grid(1, 1).fromString(value))
              .recover { case exception =>
                  println(s"An error occurred: ${exception.getMessage}"); None
              },
          10.seconds
        )
    }

    def flagCell(row: Int, column: Int): Option[IGrid] = {
        val res = fromUrl(baseUrl + "/flag/" + row + "/" + column)
        Await.result(
          res.map(value => new Grid(1, 1).fromString(value))
              .recover { case exception =>
                  println(s"An error occurred: ${exception.getMessage}"); None
              },
          10.seconds
        )
    }

    def undo(): IGrid = {
        val res = fromUrl(baseUrl + "/undo")
        Await.result(
          res.map(value => unpack(new Grid(1, 1).fromString(value)))
              .recover { case exception =>
                  println(s"An error occurred: ${exception.getMessage}"); new Grid(1, 1)
              },
          10.seconds
        )
    }

    def redo(): IGrid = {
        val res = fromUrl(baseUrl + "/redo")
        Await.result(
          res.map(value => unpack(new Grid(1, 1).fromString(value)))
              .recover { case exception =>
                  println(s"An error occurred: ${exception.getMessage}"); new Grid(1, 1)
              },
          10.seconds
        )
    }

    def save: Unit = {
        val res = fromUrl(baseUrl + "/save")
        res.onComplete(_ => {
            println("save called")
            notifyObservers
        })
    }

    def load: IGrid = {
        val res = fromUrl(baseUrl + "/load")
        Await.result(
          res.map(value => unpack(new Grid(1, 1).fromString(value)))
              .recover { case exception =>
                  println(s"An error occurred: ${exception.getMessage}"); new Grid(1, 1)
              },
          10.seconds
        )
    }

    def openGrid: IGrid = {
        val res = fromUrl(baseUrl + "/openGrid")
        Await.result(
          res.map(value => unpack(new Grid(1, 1).fromString(value)))
              .recover { case exception =>
                  println(s"An error occurred: ${exception.getMessage}"); new Grid(1, 1)
              },
          10.seconds
        )
    }

    def getGrid: IGrid = {
        val res = fromUrl(baseUrl + "/getGrid")
        Await.result(
          res.map(value => unpack(new Grid(1, 1).fromString(value)))
              .recover { case exception =>
                  println(s"An error occurred: ${exception.getMessage}"); new Grid(1, 1)
              },
          10.seconds
        )
    }

    def getMines: List[Option[ICell]] = {
        val res = fromUrl(baseUrl + "/getMines")
        Await.result(
          res.map(_ => List[Option[ICell]]())
              .recover { case exception =>
                  println(s"An error occurred: ${exception.getMessage}"); List[Option[ICell]]()
              },
          10.seconds
        )
    }

    def fromUrl(url: String): Future[String] = {
        Future {
            scala.io.Source.fromURL(url).mkString
        }
    }

    def unpack(option: Option[IGrid]): IGrid = {
        option.getOrElse(new Grid(1, 1))
    }
}
