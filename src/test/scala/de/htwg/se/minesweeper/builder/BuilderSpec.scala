package de.htwg.se.minesweeper.builder

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec


class ControllerSpec extends AnyWordSpec {
    "A Builder" should {
        var builder = new ControllerBuilder()
        
        "have a easy grid" in {
            var controller = builder.difficulty("easy").random("random").getResult 
            controller.getGrid.getWidth should be (9)
            controller.getGrid.getHeight should be (9)
        }

        "have a medium grid" in {
            var controller = builder.difficulty("medium").random("random").getResult
            controller.getGrid.getWidth should be (16)
            controller.getGrid.getHeight should be (16)
        }

        "have a hard grid" in {
            var controller = builder.difficulty("hard").random("random").getResult  
            controller.getGrid.getWidth should be (30)
            controller.getGrid.getHeight should be (16)
        }
    }
}