package de.htwg.se.minesweeper.model.cell

import de.htwg.se.minesweeper.model.cell._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class CellFactorySpec extends AnyWordSpec {

    "A cell factory" should {
        "have hidden cell: false, true" in {
            val cell = CellFactory("hidden", 0)
            cell.isHidden should be(true)
            cell.isFlagged should be(false)
        }
        "be flaggable" in {
            val cell = CellFactory("flagged", 0)
            cell.isHidden should be(true)
            cell.isFlagged should be(true)
        }
        "be openable" in {
            var cell = CellFactory("open", 0)
            cell.isHidden should be(false)
        }

        "detect if its a mine" in {
            val mine = CellFactory("hidden", -1)
            val cell = CellFactory("hidden", 0)

            mine.isMine should be(true)
            cell.isMine should be(false)
        }
        "have a value" in {
            val cell = CellFactory("hidden", 0)
            cell.getValue should be(0)
        }
    }
}

