package de.htwg.se.minesweeper.controller

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.generator._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.model.cell._
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.fileIO._

object TestModule {
    given IRandomProvider     = MockRandomProvider()
    given Difficulty          = Difficulty.Easy
    given IDifficultyProvider = DifficultyProvider()
    given IFileIO = FileIOToXML()
    given IGenerator          = MineGridGenerator()
}

import TestModule.{given}

class ControllerSpec extends AnyWordSpec {
    

    "A Controller" should {
        var controller = new Controller()
        "open cell" in {
            var grid = controller.openCell(0, 0).get
            grid.getCell(0, 0).isHidden should be(false)

            controller.openCell(20,20) should be(None)
        }

        "open recursive cells" in {
            var grid = controller.openCell(0, 8).get
            grid.getCell(0, 7).isHidden should be(false)
            grid.getCell(0, 8).isHidden should be(false)
            grid.getCell(1, 7).isHidden should be(false)
            grid.getCell(1, 8).isHidden should be(false)
            grid.getCell(2, 7).isHidden should be(false)
            grid.getCell(2, 8).isHidden should be(false)
            grid = controller.undo()
            grid.getCell(0, 7).isHidden should be(true)
            grid.getCell(0, 8).isHidden should be(true)
            grid.getCell(1, 7).isHidden should be(true)
            grid.getCell(1, 8).isHidden should be(true)
            grid.getCell(2, 7).isHidden should be(true)
            grid.getCell(2, 8).isHidden should be(true)
            grid = controller.redo()
            grid.getCell(0, 7).isHidden should be(false)
            grid.getCell(0, 8).isHidden should be(false)
            grid.getCell(1, 7).isHidden should be(false)
            grid.getCell(1, 8).isHidden should be(false)
            grid.getCell(2, 7).isHidden should be(false)
            grid.getCell(2, 8).isHidden should be(false)
        }

        "flag cell" in {
            var grid = controller.flagCell(1, 1).get
            grid.getCell(1, 1).isFlagged should be(true)
            grid = controller.flagCell(1, 1).get
            grid.getCell(1, 1).isFlagged should be(false)
            grid = controller.undo()
            grid.getCell(1, 1).isFlagged should be(true)
            grid = controller.undo()
            grid.getCell(1, 1).isFlagged should be(false)
            grid = controller.redo()
            grid.getCell(1, 1).isFlagged should be(true)
            grid = controller.redo()
            grid.getCell(1, 1).isFlagged should be(false)

            controller.flagCell(20,20) should be(None)
        }
        "ignore flag when cell is open" in {
            var grid = controller.flagCell(0, 0).get
            grid.getCell(0, 0).isFlagged should be(false)
            grid.getCell(0, 0).isHidden should be(false)
        }

        "ignore open when cell is flagged" in {
            var grid = controller.flagCell(1, 1).get
            controller.openCell(1, 1) should be(None)
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
            controller.validateCoordinates(0, 0) should be(true)
            controller.validateCoordinates(-1, 0) should be(false)
            controller.validateCoordinates(0, -1) should be(false)
            controller.validateCoordinates(9, 0) should be(false)
            controller.validateCoordinates(0, 9) should be(false)
        }

        "undo and redo commands" in {
            controller = new Controller()
            var grid = controller.openGrid
            for (c <- grid)
                c.isHidden should be(false)
            grid = controller.undo()
            for (c <- grid)
                c.isHidden should be(true)
            //println(grid)
            grid = controller.redo()
            for (c <- grid)
                c.isHidden should be(false)
        }

        "save and load" in {
            controller.save

            controller = new Controller()
            
            controller.load

            var grid = controller.grid

            grid.getCell(4, 4).isHidden should be(false)
            grid.getCell(5, 5).isHidden should be(false)

        }

    }

}
