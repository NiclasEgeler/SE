package de.htwg.se.minesweeper.util

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class incrCommand extends ICommand[Int]:
    override def doStep(state: Int): Int   = state + 1
    override def undoStep(state: Int): Int = state - 1
    override def redoStep(state: Int): Int = state + 1

class CommandSpec extends AnyWordSpec {
    "A Command" should {
        val command = new incrCommand
        "have a do step" in {
            command.doStep(0) should be(1)
            command.doStep(1) should be(2)
        }
        "have an undo step" in {
            command.undoStep(1) should be(0)
        }
        "have a redo step" in {
            command.redoStep(1) should be(2)
        }
    }
}
