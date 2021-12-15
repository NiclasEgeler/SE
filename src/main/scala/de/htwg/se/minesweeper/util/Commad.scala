package de.htwg.se.minesweeper.util

trait ICommand[T]:
    /*def noStep(t: T): T*/
    def doStep(t: T): T
    def undoStep(t: T): T
    def redoStep(t: T): T

class UndoManager[T]:
    private var undoStack: List[ICommand[T]] = Nil
    private var redoStack: List[ICommand[T]] = Nil
    def doStep(t: T, command: ICommand[T]): T = {
        undoStack = command :: undoStack
        return command.doStep(t)
    }

    def undoStep(t: T): T = {
        return undoStack match {
            case Nil => t
            case head :: stack => {
                val result = head.undoStep(t)
                undoStack = stack
                redoStack = head :: redoStack
                result
            }
        }
    }
    def redoStep(t: T): T = {
        return redoStack match {
            case Nil => t
            case head :: stack => {
                val result = head.redoStep(t)
                redoStack = stack
                undoStack = head :: undoStack
                result
            }
        }
    }
