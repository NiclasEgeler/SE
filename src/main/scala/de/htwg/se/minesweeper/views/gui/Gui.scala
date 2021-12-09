package de.htwg.se.minesweeper
package views.gui

import scalafx.Includes._
import scalafx.animation.{Interpolator, Timeline}
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.geometry.Insets
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text
import scalafx.scene.effect.DropShadow
import scalafx.geometry.Insets
import scalafx.scene.layout.HBox
import scalafx.scene.paint.*

class GUI extends JFXApp3 {
    // controller.add(this)
    def run = println("GUI starting to run..."); start()
    // override def update: Unit = {
    //     println("Shit updated")
    // }
    println("called")
    override def start(): Unit = {
        println("start")
        stage = new JFXApp3.PrimaryStage {
            //    initStyle(StageStyle.Unified)
            title = "ScalaFX Hello World"
            scene = new Scene {
                fill = Color.rgb(38, 38, 38)
                content = new HBox {
                    padding = Insets(50, 80, 50, 80)
                    children = Seq(
                      new Text {
                          text = "Scala"
                          style = "-fx-font: normal bold 100pt sans-serif"
                          fill = new LinearGradient(endX = 0, stops = Stops(Red, DarkRed))
                      },
                      new Text {
                          text = "FX"
                          style = "-fx-font: italic bold 100pt sans-serif"
                          fill = new LinearGradient(
                            endX = 0,
                            stops = Stops(White, DarkGray)
                          )
                          effect = new DropShadow {
                              color = DarkGray
                              radius = 15
                              spread = 0.25
                          }
                      }
                    )
                }
            }
        }
    }
}
