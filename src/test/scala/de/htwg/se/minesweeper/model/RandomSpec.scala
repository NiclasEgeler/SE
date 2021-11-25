package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.random._


class RandomSpec extends AnyWordSpec {

    "A random provider" should {
        "be between x and y" in {
            val random = new RandomProvider()
            var value = random.between(1,10)
            (value >= 1 && value < 10) should be(true)

            value = random.between(8,9)
            value should be(8)

            value = random.between(10,12)
            (value >= 10 && value < 12) should be(true)

            value = random.between(1,100)
            (value >= 1 && value < 100) should be(true)
            
        }
    }
}
