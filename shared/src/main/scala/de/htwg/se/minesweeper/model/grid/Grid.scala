package de.htwg.se.minesweeper.model.grid
import scala.collection._
import de.htwg.se.minesweeper.model.cell._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import io.circe.{Decoder, Encoder, HCursor, Json}
import scala.io.StdIn
import scala.concurrent.ExecutionContext

case class Grid(grid: Vector[Vector[ICell]]) extends IGrid {

    def this(rows: Int, columns: Int) =
        this(Vector.tabulate(rows, columns) { (row, col) => CellFactory("hidden", 0) })

    def setCell(row: Int, col: Int, cell: ICell): IGrid = {
        return copy(grid.updated(row, grid(row).updated(col, cell)))
    }

    def getCell(row: Int, col: Int): ICell = {
        return grid(row)(col)
    }

    def iterator: Iterator[ICell] = new AbstractIterator[ICell] {
        private var row = 0
        private var col = 0

        def hasNext = row < getHeight
        def next(): ICell = {
            val cell = getCell(row, col)
            col += 1
            if (col >= getWidth) {
                col = 0
                row += 1
            }
            return cell
        }
    }

    def getRow(row: Int): Vector[ICell] = grid(row)

    def getWidth: Int = grid(0).size

    def getHeight: Int = grid.size

    override def toString: String = grid.asJson.noSpaces

    def fromString(source: String): Option[IGrid] = decode[IGrid](source) match
        case Right(v: IGrid) => return Some(v)
        case Left(_)         => return None

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
