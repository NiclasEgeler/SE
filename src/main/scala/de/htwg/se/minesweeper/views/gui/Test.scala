package de.htwg.se.minesweeper.views.gui

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


object Test extends JFXApp3 {
  override def start() : Unit = {
    stage = new PrimaryStage {
      title = "Simple ScalaFX App"
      scene = new Scene {
        content = new HBox {
          padding = Insets(50, 80, 50, 80)
          children = Seq(
            new Text {
              text = "Scala"
              style = "-fx-font: normal bold 100pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(Red, DarkRed))
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