package de.htwg.se.minesweeper.views.tui

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.Grid
import de.htwg.se.minesweeper.views.tui.Tui
import de.htwg.se.minesweeper.model.Difficulty
val eol = sys.props("line.separator")

class TuiSpec extends AnyWordSpec {
    val tui = new Tui(new Grid(Difficulty.Easy))

    "Tui" should {
        "have a topBar       ╭───┬───┬───┬───┬───┬───┬───┬───┬───╮" in {
            tui.topBar() should be("╭───┬───┬───┬───┬───┬───┬───┬───┬───╮" + eol)
        }

        "have vertical lines │   │   │   │   │   │   │   │   │   │" in {
            tui.verticalLines() should be("│   │   │   │   │   │   │   │   │   │" + eol)
        }

        "have a centerBar    ├───┼───┼───┼───┼───┼───┼───┼───┼───┤" in {
            tui.centerBar() should be(
              "├───┼───┼───┼───┼───┼───┼───┼───┼───┤" + eol
            )
        }
        "have a bottomBar    ╰───┴───┴───┴───┴───┴───┴───┴───┴───╯" in {
            tui.bottomBar() should be("╰───┴───┴───┴───┴───┴───┴───┴───┴───╯" + eol)
        }

        "have a scaleable topBar" in {
            tui.topBar(2) should be("╭───┬───╮" + eol)
        }

        "have a scaleable verticaleLines" in {
            tui.verticalLines(2) should be("│   │   │" + eol)
        }

        "have a scaleable centerBar" in {
            tui.centerBar(2) should be("├───┼───┤" + eol)
        }

        "have a scaleable bottomBar" in {
            tui.bottomBar(2) should be("╰───┴───╯" + eol)
        }

        "have a scaleable grid" in {
            tui.grid(2, 2) should be(
              "╭───┬───╮" + eol + "│   │   │" + eol + "├───┼───┤" + eol + "│   │   │" + eol + "╰───┴───╯" + eol
            )
        }
    }
}
