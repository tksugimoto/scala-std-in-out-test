object Main extends App {
  import scala.io.StdIn._
  val n = readInt()
  for(_ <- 1 to n) {
    val a = readInt()
    println(s"${a * 2}")
  }
}
