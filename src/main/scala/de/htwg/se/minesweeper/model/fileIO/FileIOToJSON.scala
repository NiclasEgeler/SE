package de.htwg.se.minesweeper.model.fileIO

import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.cell._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import io.circe.{Decoder, Encoder, HCursor, Json}
import scala.io.Source
import java.io._


class FileIOToJSON extends IFileIO {
    override def load: Option[IGrid] = {
        val source: String = Source.fromFile("grid.json").getLines.mkString
        print(source)
        decode[IGrid](source) match
          case Right(v: IGrid) => return Some(v)
          case Left(_) => return None
    }

    override def save(grid: IGrid): Unit = {
        val pw = new PrintWriter(new File("grid.json"))
        pw.write(grid.asJson.toString)
        pw.close()
    }
    implicit val encodeGrid: Encoder[IGrid] = new Encoder[IGrid] {
        final def apply(a: IGrid): Json = Json.obj(
          ("cells", (for (i <- 0 until a.getHeight) yield a.getRow(i)).asJson)
        )
    }

    implicit val decodeGrid: Decoder[IGrid] = new Decoder[IGrid] {
        final def apply(c: HCursor): Decoder.Result[IGrid] =
            for {
                cells <- c.downField("cells").as[Vector[Vector[ICell]]]
            } yield {
                new Grid(cells)
            }
    }

    implicit val encodeCell: Encoder[ICell] = new Encoder[ICell] {
        final def apply(a: ICell): Json = Json.obj(
          ("value", Json.fromInt(a.getValue)),
          ("type", Json.fromString(cellToClass(a)))
        )
    }

    implicit val decodeCell: Decoder[ICell] = new Decoder[ICell] {
        final def apply(c: HCursor): Decoder.Result[ICell] =
            for {
                cellValue <- c.downField("value").as[Int]
                cellType  <- c.downField("type").as[String]
            } yield {
                CellFactory(cellType, cellValue)
            }
    }
}


def cellToClass(x: ICell): String = {
    x match {
        case HiddenCell(_) => "hidden"
        case OpenCell(_)   => "open"
        case FlagCell(_)   => "flagged"
        case _             => "hidden"
    }
}
