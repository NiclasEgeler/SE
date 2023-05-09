package de.htwg.se.minesweeper.fileIO

import scala.annotation.targetName
import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.api.*


class Cell(tag: Tag, grid: TableQuery[Grid]) extends Table[(Int, Int, Int, Int, String)](tag, "Cell") {
    def gridId = column[Int]("GRID_ID")
    
    def row = column[Int]("ROW")

    def col = column[Int]("COL")

    def value = column[Int]("VALUE")

    def typeName = column[String]("TYPE_NAME")

    def * = (gridId, row, col, value, typeName)

    def pk = primaryKey("PK_CELL", (gridId, row, col))

    def gridFk = foreignKey("FK_GRID", gridId, grid)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
}