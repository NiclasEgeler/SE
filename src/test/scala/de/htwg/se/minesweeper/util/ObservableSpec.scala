package de.htwg.se.minesweeper.util

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper._
class ObservableSpec extends AnyWordSpec {

    class mockObserver extends IObserver {
        var msg                     = 0
        override def update: Unit = { msg += 1 }
    }

    val obs = new Observable()
    "Observable" should {
        "have no subscribors" in {
            obs.subscribers.isEmpty should be(true)
        }
        "have subscribers" in {
            obs.add(new mockObserver())
            obs.add(new mockObserver())
            obs.subscribers.size should be(2)

        }
        "remove subscribers" in {
            val tmp = new mockObserver()
            obs.add(tmp)
            val preRemove = obs.subscribers.size
            obs.remove(tmp)
            preRemove should be(3)
            obs.subscribers.size should be(2)
        }
        "observe subscribers" in {
            val tmp = new mockObserver()
            obs.add(tmp)
            obs.notifyObservers
            tmp.msg should be(1)
        }
    }
}
