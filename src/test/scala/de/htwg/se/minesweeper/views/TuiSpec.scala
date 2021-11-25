package de.htwg.se.minesweeper.views.tui

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.generator._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.views.tui._
import de.htwg.se.minesweeper.controller._
import de.htwg.se.minesweeper.builder._


val eol = sys.props("line.separator")

class TuiSpec extends AnyWordSpec {
    var builder = new ControllerBuilder()
    var controller = builder.difficulty("easy").random("mock").getResult
    val tui        = new Tui(controller)

    "Tui" should {
        "have a xAxis          1   2   3   4   5   6   7   8   9" in {
            tui.xAxis() should be("  1   2   3   4   5   6   7   8   9   " + eol)
        }
        "have a topBar       ╭───┬───┬───┬───┬───┬───┬───┬───┬───╮" in {
            tui.topBar() should be("╭───┬───┬───┬───┬───┬───┬───┬───┬───╮" + eol)
        }

        "have vertical lines │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ 1" in {
            tui.verticalLines(controller.getGrid) should be(
              "│ ? │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ 1" + eol
            )
        }

        "have a centerBar    ├───┼───┼───┼───┼───┼───┼───┼───┼───┤" in {
            tui.centerBar() should be(
              "├───┼───┼───┼───┼───┼───┼───┼───┼───┤" + eol
            )
        }
        "have a bottomBar    ╰───┴───┴───┴───┴───┴───┴───┴───┴───╯" in {
            tui.bottomBar() should be("╰───┴───┴───┴───┴───┴───┴───┴───┴───╯" + eol)
        }

        "have a scaleable xAxis" in {
            tui.xAxis(2) should be("  1   2   " + eol)
        }

        "have a scaleable topBar" in {
            tui.topBar(2) should be("╭───┬───╮" + eol)
        }

        "have a scaleable verticaleLines" in {
            tui.verticalLines(new Grid(2, 2), 0) should be("│ ? │ ? │ 1" + eol)
        }

        "have a scaleable centerBar" in {
            tui.centerBar(2) should be("├───┼───┤" + eol)
        }

        "have a scaleable bottomBar" in {
            tui.bottomBar(2) should be("╰───┴───╯" + eol)
        }

        "have a scaleable grid" in {
            tui.grid(new Grid(2, 2)) should be(
              "  1   2   " + eol + "╭───┬───╮" + eol + "│ ? │ ? │ 1" + eol + "├───┼───┤" + eol + "│ ? │ ? │ 2" + eol + "╰───┴───╯" + eol
            )
        }

        "print cells" in {
            tui.printCell(new Cell(0,false,true)) should be("?")
            tui.printCell(new Cell(1,false,false)) should be("1")
            tui.printCell(new Cell(0,false,false)) should be(" ")
            tui.printCell(new Cell(0,true,true)) should be("⚑")
            tui.printCell(new Cell(-1,false,false)) should be("#")
        }
    }
}
