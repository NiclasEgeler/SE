package de.htwg.se.minesweeper.util

trait IObserver {
    def update: Unit
}


class Observable {
    var subscribers: Vector[IObserver] = Vector()

    def add(s: IObserver): Unit = subscribers = subscribers :+ s

    def remove(s: IObserver): Unit = subscribers = subscribers.filterNot(o => o == s)

    def notifyObservers: Unit = subscribers.foreach(o => o.update)
}
