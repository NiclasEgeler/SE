package de.htwg.se.minesweeper.fileIO

import scala.util.Try
import de.htwg.se.minesweeper.model.grid.IGrid
import slick.jdbc.MySQLProfile.api.*
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Await
import scala.concurrent.duration._
import de.htwg.se.minesweeper.model.cell.{HiddenCell, FlagCell, OpenCell, ICell}
import de.htwg.se.minesweeper.fileIO.IFileIO

class FileSlick extends IFileIO {
    private val databaseUrl: String =
        s"jdbc:mysql://" +
            "0.0.0.0:" +
            s"3306/" +
            s"db?serverTimezone=CET"

    private val database = Database.forURL(
      url = databaseUrl,
      driver = "com.mysql.cj.jdbc.Driver",
      user = "user",
      password = "password"
    )
    private val gridTable = new TableQuery(new Grid(_))
    private val cellTable = new TableQuery(new Cell(_, gridTable))

    private val waitTime = Duration(15, SECONDS)

    private val setup: DBIOAction[Unit, NoStream, Effect.Schema] =
        println("Setup")
        DBIO.seq(gridTable.schema.createIfNotExists, cellTable.schema.createIfNotExists)

    Try(Await.result(database.run(setup), waitTime)) match
        case Success(value)     => println("DB Connection established")
        case Failure(exception) => println(exception.getMessage())

    override def save(grid: IGrid) = {
        val insertGrid = (gridTable returning gridTable.map(_.id)) += (0, "test", grid.getHeight,grid.getWidth)
        var gridId = Await.result(database.run(insertGrid), waitTime)
        println(gridId)
        for {
            r <- 0 until grid.getWidth
            c <- 0 until grid.getHeight
        } {
          val cell = grid.getCell(r, c)
          val insertCell = cellTable += (gridId, r, c, cell.getValue, cellToClass(cell))
          Await.result(database.run(insertCell), waitTime)
        }        
        
    }

    override def load: Option[IGrid] = ???

    def cellToClass(x: ICell): String = {
    x match {
        case HiddenCell(_) => "hidden"
        case OpenCell(_)   => "open"
        case FlagCell(_)   => "flagged"
        case _             => "hidden"
    }
}

}
