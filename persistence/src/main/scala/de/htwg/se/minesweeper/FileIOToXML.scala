package de.htwg.se.minesweeper.fileIO

import de.htwg.se.minesweeper.model.grid._
import scala.io.Source
import scala.xml.{NodeSeq, PrettyPrinter}
import scala.xml.XML
import de.htwg.se.minesweeper.model.cell._

import java.io._

class FileIOToXML extends IFileIO {
    override def load: Option[IGrid] = {
        val source = XML.loadFile("grid.xml")
        // Scala syntax = WayTooDank
        val result = Vector[Vector[ICell]]() ++ (for (row <- (source \\ "row"))
            yield (Vector[ICell]() ++ (for (cell <- (row \\ "cell"))
                yield CellFactory((cell \ "@type").text, cell.text.trim.toInt))))
        return Some(new Grid(result))
    }

    override def save(grid: IGrid): Unit = {
        XML.save("grid.xml", gridToXML(grid))
    }

    def gridToXML(grid: IGrid): xml.Node = {
        <grid> 
      {
            (for (i <- 0 until grid.getWidth) yield rowToXML(grid.getRow(i)))
        }
      </grid>
    }

    def rowToXML(row: Vector[ICell]): xml.Node = {
        <row>
        {
            (for (i <- 0 until row.length) yield cellToXml(row(i)))
        }
      </row>
    }

    def cellToXml(cell: ICell) = {
        <cell type={cellToClass(cell)}>
             {cell.getValue}
        </cell>
    }

    def cellToClass(x: ICell): String = {
        x match {
            case HiddenCell(_) => "hidden"
            case OpenCell(_)   => "open"
            case FlagCell(_)   => "flagged"
            case _             => "hidden"
        }
    }
}
