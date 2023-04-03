package de.htwg.se.minesweeper.model.generator

import de.htwg.se.minesweeper.model.cell._
import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.random._
import de.htwg.se.minesweeper.model.difficulty._
import de.htwg.se.minesweeper.model.cell._
import de.htwg.se.minesweeper.model.Directions
import de.htwg.se.minesweeper.controller.commands.FlagCommand

class MineGridGenerator(using random: IRandomProvider)(using difficulty: IDifficultyProvider)
    extends IGenerator {

    def generate(): IGrid = generate(difficulty.get);

    private def generate(difficulty: Difficulty): IGrid =
        generate(difficulty.rows, difficulty.columns, difficulty.numMines);

    private def generate(rows: Int, columns: Int, mines: Int): IGrid = {
        val mineCount   = mines
        val grid: IGrid = new Grid(rows, columns)

        val gridWithMines = placeMines(grid, mineCount, rows, columns)

        return (for {
            r <- 0 until rows
            c <- 0 until columns
        } yield {
            (r, c)
        }).map((r, c) => {
            val cell = gridWithMines.getCell(r, c)

            val updatedCell =
                if (cell.isMine) CellFactory("hidden", -1)
                else CellFactory("hidden", getMineCount(r, c, gridWithMines))

            (r, c, updatedCell)
        }).foldLeft(gridWithMines)((accGrid, cellInfo) => {
            val (r, c, updatedCell) = cellInfo
            accGrid.setCell(r, c, updatedCell)
        })
    }

    def placeMines(grid: IGrid, count: Int, rows: Int, columns: Int): IGrid = {
        if (count <= 0) return grid

        val rowIn    = random.between(0, rows)
        val columnIn = random.between(0, columns)

        if ((grid.getCell(rowIn, columnIn)).isMine) return placeMines(grid, count, rows, columns)

        val newGrid = grid.setCell(rowIn, columnIn, CellFactory("hidden", -1))
        placeMines(newGrid, count - 1, rows, columns)
    }

    private def getMineCount(row: Int, column: Int, grid: IGrid): Int =
        Directions.values.count(d => checkCell(d)(row, column, grid))

    private def checkCell(d: Directions)(row: Int, column: Int, grid: IGrid): Boolean = {
        val x = column + d.x
        val y = row + d.y
        if (x >= 0 && y >= 0 && grid.getHeight > y && grid.getWidth > x)
            grid.getCell(y, x).isMine
        else
            false
    }

    // Warum geht _ nicht?
    private def checkUpwards(row: Int, column: Int, grid: IGrid): Boolean =
        checkCell(Directions.Up)(row, column, grid)
    private def checkDownwards(row: Int, column: Int, grid: IGrid): Boolean =
        checkCell(Directions.Down)(row, column, grid)
    private def checkLeft(row: Int, column: Int, grid: IGrid): Boolean =
        checkCell(Directions.Left)(row, column, grid)
    private def checkRight(row: Int, column: Int, grid: IGrid): Boolean =
        checkCell(Directions.Right)(row, column, grid)
}
