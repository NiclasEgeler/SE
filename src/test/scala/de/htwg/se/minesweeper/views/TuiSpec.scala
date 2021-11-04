package de.htwg.se.minesweeper.views.tui
import de.htwg.se.minesweeper.model.Cell;
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.Grid
import de.htwg.se.minesweeper.views.tui.Tui
import de.htwg.se.minesweeper.model.Difficulty

val eol = sys.props("line.separator")

class TuiSpec extends AnyWordSpec {
    val tui = new Tui(new Grid(Vector.tabulate(9, 9) { (row, col) => new Cell(0) }))

    "Tui" should {
        "have a xAxis          1   2   3   4   5   6   7   8   9" in {
            tui.xAxis() should be("  1   2   3   4   5   6   7   8   9   " + eol)
        }
        "have a topBar       ╭───┬───┬───┬───┬───┬───┬───┬───┬───╮" in {
            tui.topBar() should be("╭───┬───┬───┬───┬───┬───┬───┬───┬───╮" + eol)
        }

        "have vertical lines │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ 1" in {
            tui.verticalLines() should be("│ ? │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ ? │ 1" + eol)
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
            tui.verticalLines(2) should be("│ ? │ ? │ 1" + eol)
        }

        "have a scaleable centerBar" in {
            tui.centerBar(2) should be("├───┼───┤" + eol)
        }

        "have a scaleable bottomBar" in {
            tui.bottomBar(2) should be("╰───┴───╯" + eol)
        }

        "have a scaleable grid" in {
            tui.grid(2, 2) should be(
              "  1   2   " + eol + "╭───┬───╮" + eol + "│ ? │ ? │ 1" + eol + "├───┼───┤" + eol + "│ ? │ ? │ 2" + eol + "╰───┴───╯" + eol
            )
        }
    }
}
