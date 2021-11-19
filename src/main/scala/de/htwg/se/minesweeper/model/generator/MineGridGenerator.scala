package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.model._

class MineGridGenerator(val random: IRandomProvider, val difficulty: IDifficultyProvider)
    extends IGenerator {

    def generate() : Grid = generate(difficulty.get);

    private def generate(difficulty: Difficulty): Grid =
        generate(difficulty.rows, difficulty.columns, difficulty.numMines);

    private def generate(rows: Int, columns: Int, mines: Int): Grid = {
        var count = mines;
        var grid  = new Grid(rows, columns)
        while (count > 0) {
            var rowIn    = random.between(0, rows)
            var columnIn = random.between(0, columns)
            if ((grid.getCell(rowIn, columnIn)).isMine != true) {
                grid = grid.setCell(rowIn, columnIn, new Cell(-1, false, true))
                count -= 1;
            }
        }
        for (r <- 0 until rows) {
            for (c <- 0 until columns) {
                if (!grid.getCell(r, c).isMine)
                    grid = grid.setCell(r, c, new Cell(getMineCount(r, c, grid), false, true))
            }
        }
        return grid
    }

    private def getMineCount(row: Int, column: Int, grid: Grid): Int = {
        var mineCount = 0
        for (d <- Directions.values)
            if (checkCell(row, column, grid, d))
                mineCount += 1
        return mineCount
    }

    private def checkCell(row: Int, column: Int, grid: Grid, d: Directions): Boolean = {
        var x = row + d.x
        var y = column + d.y
        if (x >= 0 && y >= 0 && grid.getHeight > y && grid.getWidth > x)
            return grid.getCell(x, y).isMine
        return false

    }

}
