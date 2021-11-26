package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.cell._

class GridSpec extends AnyWordSpec {

    "A grid contains a two-dimentional Vector of Cells. A Grid" when {
        "empty " should {
            "be created with parameters" in {
                val grid = new Grid(4, 7)
                grid.getWidth should be(7)
                grid.getHeight should be(4)
            }
            "create Vector" in {
                val grid = new Grid(Vector.tabulate(9, 9) { (row, col) =>
                    CellFactory("hidden", 0)
                })
                grid.getWidth should be(9)
                grid.getHeight should be(9)
            }
        }
        "filled" should {
            val grid = new Grid(Vector.tabulate(9, 9) { (row, col) => CellFactory("hidden", 0) })
            "get cell" in {
                grid.getCell(1, 1) should be(CellFactory("hidden", 0))
            }

            "set cell" in {
                val newGrid = grid.setCell(8, 8, CellFactory("flagged", 69))
                newGrid.getCell(8, 8) should be(CellFactory("flagged", 69))
            }

            "get row" in {
                grid.getRow(0) should be(Vector.tabulate(9) { (row) => CellFactory("hidden", 0) })
            }
        }
    }

}
