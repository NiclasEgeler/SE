package de.htwg.se.minesweeper.controller

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.generator._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.model._

class ControllerSpec extends AnyWordSpec {
    "A Controller" should {
        var generator =
            new MineGridGenerator(new MockRandomProvider(), new DifficultyProvider(Difficulty.Easy))
        var controller = new Controller(generator)
        "open cell" in {
            var grid = controller.openCell(0, 0)
            grid.getCell(0, 0).isHidden should be(false)
        }

        "flag cell" in {
            var grid = controller.flagCell(1, 1)
            grid.getCell(1, 1).isFlagged should be(true)
            grid = controller.flagCell(1, 1)
            grid.getCell(1, 1).isFlagged should be(false)
        }
        "ignore flag when cell is open" in {
            var grid = controller.flagCell(0, 0)
            grid.getCell(0, 0).isFlagged should be(false)
            grid.getCell(0, 0).isHidden should be(false)
        }

        "ignore open when cell is flagged" in {
            var grid = controller.flagCell(1, 1)
            grid = controller.openCell(1, 1)
            grid.getCell(1, 1).isHidden should be(true)
            grid.getCell(1, 1).isFlagged should be(true)
        }

        "open grid" in {
            var grid = controller.openGrid
            grid.getCell(2, 2).isHidden should be(false)
            grid.getCell(3, 3).isHidden should be(false)
        }

        "get grid" in {
            var grid = controller.getGrid
            grid.getCell(4, 4).isHidden should be(false)
            grid.getCell(5, 5).isHidden should be(false)
        }

        "validate coordinates" in {
            var grid = controller.getGrid
            controller.validateCoordinates(0,0) should be(true)
            controller.validateCoordinates(-1,0) should be(false)
        }
    }

}
