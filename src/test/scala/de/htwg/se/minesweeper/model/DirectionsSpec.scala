package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class DirectionsSpec extends AnyWordSpec {

    "Directions" when {
        "UpLeft" should {
            "x -1, y -1" in {
                val dif = Directions.UpLeft
                dif.x should be(-1)
                dif.y should be(-1)
            }
        }
        "Up" should {
            "x -1, y -1" in {
                val dif = Directions.Up
                dif.x should be(0)
                dif.y should be(1)
            }
        }
        "UpRight" should {
            "x 1, y -1" in {
                val dif = Directions.UpRight
                dif.x should be(1)
                dif.y should be(-1)
            }
        }
        "Right" should {
            "x 1, y 0" in {
                val dif = Directions.Right
                dif.x should be(1)
                dif.y should be(0)
            }
        }
        "DownRight" should {
            "x 1, y 1" in {
                val dif = Directions.DownRight
                dif.x should be(1)
                dif.y should be(1)
            }
        }
        "Down" should {
            "x 0, y 1" in {
                val dif = Directions.Down
                dif.x should be(0)
                dif.y should be(1)
            }
        }
        "DownLeft" should {
            "x -1, y 1" in {
                val dif = Directions.DownLeft
                dif.x should be(-1)
                dif.y should be(1)
            }
        }
        "Left" should {
            "x -1, y 0" in {
                val dif = Directions.Left
                dif.x should be(-1)
                dif.y should be(0)
            }
        }
    }
}