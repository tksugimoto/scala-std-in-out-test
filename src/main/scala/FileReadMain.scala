import java.nio.file.Paths

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.FileIO
import akka.util.ByteString

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object FileReadMain extends App {
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val fileName = "file.lf.txt"
  println(readFileContentWithoutLF(fileName))

  system.terminate()

  // TODO: CRLF 対応
  
  def readFileContentWithoutLF(fileName: String)(implicit materializer: ActorMaterializer): String = {
    val LF = ByteString("\n")

    val resultFuture: Future[ByteString] =
      FileIO.fromPath(Paths.get(fileName))
          .mapConcat(_.map(ByteString(_))) // FIXME: 1byteごとに分割しているので非効率
          .filter(_ != LF)
          .runReduce(_ ++ _) // 結局結合するのでAkka Streamsのメリットがない

    val result: ByteString = Await.result(resultFuture, Duration.Inf) // FIXME: Await.result 撲滅

    result.utf8String
  }
}
