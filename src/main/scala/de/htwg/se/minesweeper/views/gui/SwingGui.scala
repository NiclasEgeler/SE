package de.htwg.se.minesweeper.views.gui

import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.util.IObserver
import de.htwg.se.minesweeper.model.cell._

import scala.swing._
import scala.swing.event._
import scala.swing.Publisher
import java.util.ArrayList
import scalafx.scene.shape.StrokeLineCap.Butt
import javax.swing.BoxLayout

class SwingGui(using controller: IController) extends IObserver {
    controller.add(this)
    var lcb = List[CellButton]()

    for (
      c <- controller.getGrid.zip(
        0 until (controller.getGrid.getHeight * controller.getGrid.getWidth)
      )
    )
        lcb = lcb :+ (
          new CellButton(
            printCell(c._1),
            Math.floor(c._2 / (controller.getGrid.getWidth.toDouble)).toInt,
            c._2 % controller.getGrid.getWidth
          ) {
              listenTo(mouse.clicks)
              reactions += { case MouseClicked(_: CellButton, _, c, _, _) =>
                  c match {
                      case 0   => controller.openCell(x, y)
                      case 256 => controller.flagCell(x, y)
                      case _   => println("YIKES DOG")
                  }
              }
          }
        )

    new Frame {
        title = "Minesweeper"
        preferredSize = new Dimension(500,500)
        contents = new BoxPanel(scala.swing.Orientation.Vertical) {
            contents += new GridPanel(0, controller.getGrid.getWidth) {
                for (b <- lcb) {
                    contents += b
                }
            }
            contents += new FlowPanel() {
                var undo = new Button("Undo") {
                    listenTo(mouse.clicks)
                    reactions += { case MouseClicked(_) => controller.undo() }
                }                
                var redo = new Button("Redo") {
                    listenTo(mouse.clicks)
                    reactions += { case MouseClicked(_) => controller.redo() }
                }
                var solve = new Button("Solve") {
                    listenTo(mouse.clicks)
                    reactions += { case MouseClicked(_) => controller.openGrid }
                }
                contents += undo
                contents += redo
                contents += solve
            }
        }
        pack()
        centerOnScreen()
        open()
    }

    override def update: Unit = {
        for (
          c <- controller.getGrid.zip(
            0 until (controller.getGrid.getHeight * controller.getGrid.getWidth)
          )
        ) {
            lcb(c._2).text = printCell(c._1)
        }
    }

    def printCell(cell: ICell): String = {
        if (cell.isFlagged) {
            return "⚑"
        }
        if (cell.isHidden) {
            return "?"
        }
        if (cell.isMine)
            return "#"
        return if (cell.getValue > 0) then cell.getValue.toString() else " "
    }
}

class CellButton(name: String, val x: Int, val y: Int) extends Button(name) {}
