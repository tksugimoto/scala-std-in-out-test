import java.io.{ByteArrayOutputStream, StringReader}

import org.scalatest._

class MainSpec extends WordSpecLike with Matchers {

  def test(input: String, expectedOutput: String): Unit = {
    val output = new ByteArrayOutputStream

    Console.withIn(new StringReader(input))  {
      Console.withOut(output) {
        Main.main(Array())
      }

      println("============== outputs ==============")
      println(output.toString)

      output.toString.trim shouldEqual expectedOutput
    }
  }

  "Main" must {
    "1行Pattern" in {
      val input = "1"

      val expectedOutput = "2"

      test(input, expectedOutput)
    }

    "複数行Pattern" in {
      val input =
        """
          |256
          |dummy
          |無視される
        """.stripMargin.trim

      val expectedOutput =
        """
          |512
        """.stripMargin.trim

      test(input, expectedOutput)
    }
  }
}
