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

      val outputArray = output.toString.trim.split("[\r\n]+")
      val expectedOutputArray = expectedOutput.split("[\r\n]+")
      (outputArray zip expectedOutputArray).zipWithIndex foreach {
        case ((result, expect), i) => assert(result == expect, s"(line ${i + 1})")
      }
    }
  }

  "Main" must {
    "1行Pattern" in {
      val input = "0"

      val expectedOutput = ""

      test(input, expectedOutput)
    }

    "入力のみ複数行Pattern" in {
      val input =
        """
          |1
          |1
        """.stripMargin.trim

      val expectedOutput = "2"

      test(input, expectedOutput)
    }

    "入力出力両方複数行Pattern" in {
      val input =
        """
          |1
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

    "複数行出力Pattern" in {
      val input =
        """
          |2
          |123
          |456
        """.stripMargin.trim

      val expectedOutput =
        """
          |246
          |912
        """.stripMargin.trim

      test(input, expectedOutput)
    }
  }
}
