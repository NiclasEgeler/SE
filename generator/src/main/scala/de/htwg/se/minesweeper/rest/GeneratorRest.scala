package de.htwg.se.minesweeper.generator

import de.htwg.se.minesweeper.generator.IGenerator
import de.htwg.se.minesweeper.model.grid._
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.Await
import scala.concurrent.duration._

class GeneratorRest extends IGenerator {
    implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
    def generate(): IGrid = {
        val url                            = "http://generator:8081/generate"
        val responseFuture: Future[String] = fetchUrl(url)

        val grid: Future[IGrid] = responseFuture
            .flatMap { res =>
                new Grid(1, 1).fromString(res) match {
                    case Some(value) => Future.successful(value)
                    case None => Future.failed(new Exception("Failed to parse response body"))
                }
            }
            .recover { case ex: Exception =>
                println(s"Failed to fetch URL: ${ex.getMessage}")
                new Grid(1, 1)
            }

        Await.result(grid, 10.seconds)
    }

    def fetchUrl(url: String): Future[String] = Future {
        scala.io.Source.fromURL(url).mkString
    }
}
