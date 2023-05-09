package de.htwg.se.minesweeper.fileIO

import scala.annotation.targetName
import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.api.*

class Grid(tag:Tag) extends Table[(Int, String, Int, Int)](tag, "Grid") {    
    def id = column[Int]("GRID_ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("PLAYER_NAME")    
    def rowCount = column[Int]("ROW_COUNT")
    def columnCount = column[Int]("COLUMN_COUNT")
    def * = (id, name, rowCount, columnCount)    
}
