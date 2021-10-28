package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.Grid
import de.htwg.se.minesweeper.model.Difficulty

class GridSpec extends AnyWordSpec {
    
    "A grid contains a two-dimentional Vector of Cells. A Grid" when{
        "empty " should {
            "be created with parameters" in {
                val grid = new Grid(4, 7, 5)
                grid.getWidth() should be(7)
                grid.getHeight() should be(4)
            }
            "create with difficulty" in {
                val grid = new Grid(Difficulty.Easy)
                grid.getWidth() should be(9)
                grid.getHeight() should be(9)
            }
        }
        "filled" should {
            val grid = new Grid(Difficulty.Easy)
            "get cell" in {
                grid.getCell(1, 1) should be(2)
            }
        }
    }


}