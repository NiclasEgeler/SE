package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class DifficultySpec extends AnyWordSpec {

    "Difficulty" when {
        "easy" should {

            "have 9 rows 9 columns 10 Mines" in {
                val dif = Difficulty.Easy
                dif.rows should be(9)
                dif.columns should be(9)
                dif.numMines should be(10)
            }

        }
        "medium" should {

            "have 16 rows 16 columns 40 Mines" in {
                val dif = Difficulty.Medium
                dif.rows should be(16)
                dif.columns should be(16)
                dif.numMines should be(40)
            }

        }
        "hard" should {
            "have 16 rows 30 columns 99 Mines" in {
                val dif = Difficulty.Hard
                dif.rows should be(16)
                dif.columns should be(30)
                dif.numMines should be(99)
            }

        }
    }
}
