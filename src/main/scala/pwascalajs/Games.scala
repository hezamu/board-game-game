package pwascalajs

import org.scalajs.dom

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.{Dynamic, JSON}
import scala.util.{Failure, Success}

@js.native
trait Game extends js.Object {
  val name: String = js.native
  val img: String = js.native
}

object Games {
  var data: js.Array[Game] = js.Array()

  def load(): Unit = {
    println("Loading game data")

    dom.document.querySelector("#image").classList.add("loading")

    dom.ext.Ajax.get("./data.json").onComplete {
      case Success(xhr) =>
        println(s"Loaded game data: ${xhr.responseText}")
        data = JSON.parse(xhr.responseText).asInstanceOf[js.Array[Game]]
      case Failure(_) => println(s"Failed to load game data.")
    }
  }
}
