package de.htwg.se.minesweeper.db

import scala.util.Try
import de.htwg.se.minesweeper.model.grid.IGrid


trait IDAO:
  def save(field: IGrid): Try[Unit]
  def load(gameId: Option[Int] = None): Try[IGrid]
  def update(gameId: Int, field: IGrid): Try[Unit]
  def delete(gameId: Option[Int]): Try[Unit]