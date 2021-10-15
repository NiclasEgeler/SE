@main def hello: Unit = 
  Minesweeper.printName()
  //To see charset in cmd set code page chcp 65001
  //check file encoding
  //println(System.getProperty("file.encoding"))
def msg = "I was compiled by Scala 3. :)"


object Minesweeper {
  def printName() = println("My game is Minesweeper")
}