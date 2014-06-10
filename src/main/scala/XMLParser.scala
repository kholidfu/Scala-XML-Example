import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import scala.xml._
import java.io._


object XMLParser extends App {

  val URL = "http://google.com/complete/search?output=toolbar&q=toyota+owners+manual"

  val req = new HttpGet(URL)
  val client = new DefaultHttpClient
  val resp = client.execute(req)
  val entity = resp.getEntity

  var content = ""

  val inputStream = entity.getContent
  content = io.Source.fromInputStream(inputStream).getLines.mkString
  inputStream.close

  val xmlData = XML.loadString(content)
  val suggests = (xmlData \\ "suggestion").map(_ \ "@data")

  suggests.foreach(println)

}

