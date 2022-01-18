package de.htwg.se.minesweeper.model.generator

import de.htwg.se.minesweeper.model.cell._
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.cell._

class MineGridGenerator(using random: IRandomProvider)(using difficulty: IDifficultyProvider)
    extends IGenerator {

    def generate() : IGrid = generate(difficulty.get);

    private def generate(difficulty: Difficulty): IGrid =
        generate(difficulty.rows, difficulty.columns, difficulty.numMines);

    private def generate(rows: Int, columns: Int, mines: Int): IGrid = {
        var count = mines;
        var grid: IGrid = new Grid(rows, columns)
        while (count > 0) {
            var rowIn    = random.between(0, rows)
            var columnIn = random.between(0, columns)
            if ((grid.getCell(rowIn, columnIn)).isMine != true) {
                grid = grid.setCell(rowIn, columnIn, CellFactory("hidden",-1))
                count -= 1;
            }
        }
        for (r <- 0 until rows) {
            for (c <- 0 until columns) {
                if (!grid.getCell(r, c).isMine)
                    grid = grid.setCell(r, c, CellFactory("hidden", getMineCount(r, c, grid)))
            }
        }
        return grid
    }

    private def getMineCount(row: Int, column: Int, grid: IGrid): Int = {
        var mineCount = 0
        for (d <- Directions.values)
            if (checkCell(row, column, grid, d))
                mineCount += 1
        return mineCount
    }

    private def checkCell(row: Int, column: Int, grid: IGrid, d: Directions): Boolean = {
        var x = column + d.x
        var y = row + d.y
        // TODO: Validator?
        if (x >= 0 && y >= 0 && grid.getHeight > y && grid.getWidth > x)
            return grid.getCell(y, x).isMine
        return false
    }

}
