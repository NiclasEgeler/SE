package de.htwg.se.minesweeper.db.slick

import de.htwg.se.minesweeper.db.IDAO
import scala.util.Try
import de.htwg.se.minesweeper.model.grid.IGrid
import slick.jdbc.MySQLProfile.api.*

object SlickDAO extends IDAO {    
    private val databaseUrl: String =
        s"jdbc:mysql://" +
            "0.0.0.0:" +
            s"3306/" +
            s"db?serverTimezone=CET"

    private val database = Database.forURL(
      url = databaseUrl,
      driver = com.mysql.cj.jdbc.Driver,
      user = "user",
      password = "password"
    )
    override def save(grid: IGrid): Try[Unit] = ???

    override def load(id: Option[Int]): Try[IGrid] = ???

    override def update(id: Int, grid: IGrid): Try[Unit] = ???

    override def delete(id: Option[Int]): Try[Unit] = ???

}
