@main def hello: Unit = 
  Minesweeper.printName()

def msg = "I was compiled by Scala 3. :)"


object Minesweeper {
  def printName() = println("My game is Minesweeper")
}