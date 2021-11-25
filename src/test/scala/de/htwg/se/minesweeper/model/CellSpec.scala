package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.Cell
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class CellSpec extends AnyWordSpec {

    "A cell " should {
        "have default values: false, true" in {
            val cell = new Cell(0)
            cell should be(Cell(0, false, true))
        }
        "be flaggable" in {
            var cell = new Cell(0)
            cell.isFlagged should be(false)
            cell = cell.setFlag(true)
            cell.isFlagged should be(true)
        }
        "be openable" in {
            var cell = new Cell(0)
            cell.isHidden should be(true)
            cell = cell.setHidden(false)
            cell.isHidden should be(false)
        }

        "detect if its a mine" in {
            val mine = new Cell(-1)
            val cell = new Cell(0)

            mine.isMine should be(true)
            cell.isMine should be(false)
        }
        "have a value" in {
            val cell = new Cell(0)
            cell.getValue should be(0)
        }
    }
}

