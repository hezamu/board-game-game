package pwascalajs

import org.scalajs.dom
import org.scalajs.dom.html.Div

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.util.{Failure, Random, Success}

@js.native
trait BoardGame extends js.Object {
  val name: String = js.native
  val img: String = js.native
  val width: Int = js.native
  val height: Int = js.native
}

object Game {
  var data: js.Array[BoardGame] = js.Array()

  var answer: Int = -1
  var correct: Int = 0
  var wrong: Int = 0

  def load(): Unit = {
    dom.ext.Ajax.get("./data.json").onComplete {
      case Success(xhr) =>
        data = JSON.parse(xhr.responseText).asInstanceOf[js.Array[Game]]
        Page.resetGame()
      case Failure(_) => println(s"Failed to load game data.")
    }
  }

  def selected(idx: Int)(clickEvent: Any): Unit = {
    if (idx == answer) {
      correct += 1
      dom.document.querySelector("#result").asInstanceOf[js.Dynamic].innerHTML = s"woo!<br />$correct / ${correct + wrong}"
      showNext()
    } else {
      wrong += 1
      dom.document.querySelector("#result").asInstanceOf[js.Dynamic].innerHTML = s"boo :(<br />$correct / ${correct + wrong}"
      showNext()
    }
  }

  def showNext(): Unit = {
    Page.showGames(Random.shuffle(0.until(data.size).toList).take(4) map data)
  }


  def resetGame(): Unit = {
    wrong = 0
    correct = 0
    dom.document.querySelector("#result").asInstanceOf[js.Dynamic].innerHTML = s"<br />$correct / ${correct + wrong}"
    Games.nextGame()
  }

  def showGames(games: Seq[Game]): Unit = {
    Seq("#nw","#ne","#sw","#se") map dom.document.querySelector zip games foreach {
      case (b: Div, g: Game) =>
        b.style.setProperty("background-image", s"url(${g.img})")

        val xoff = Random.nextInt(Math.max(1, g.width - b.clientWidth))
        b.style.setProperty("background-position-x", s"-${xoff}px")

        val yoff = Random.nextInt(Math.max(1, g.height - b.clientHeight))
        b.style.setProperty("background-position-y", s"-${yoff}px")
    }

    answer = Random.nextInt(4)
    dom.document.querySelector("#question").asInstanceOf[js.Dynamic].innerHTML = s"Find '${games(answer).name}'"
  }
}
