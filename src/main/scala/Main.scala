object Main extends App {
  import scala.io.StdIn._
  val n = readInt()
  for(_ <- 1 to n) {
    val a = readInt()
    debug(s"input: $a")
    println(s"${a * 2}")
  }

  private def debug(msg: Any): Unit = {
    System.err.println(msg)
  }
}
