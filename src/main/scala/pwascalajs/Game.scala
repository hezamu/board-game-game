package pwascalajs

import org.scalajs.dom

import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
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

  var answer: Int = 0
  var score: Int = 0
  var gameOn: Boolean = true

  def load(): Unit = dom.ext.Ajax.get("./data.json") onComplete {
    case Success(xhr) =>
      data = JSON.parse(xhr.responseText).asInstanceOf[js.Array[BoardGame]]
      resetGame()

    case Failure(_) => println(s"Failed to load game data.")
  }

  def resetGame(): Unit = {
    score = 0
    gameOn = true
    Page.hideSummary()
    Page.enableGameButtons()
    Page.showScore(s"Score: $score")
    showNext()
  }

  def selected(idx: Int): Unit = if(gameOn) {
    if (idx == answer) {
      score += 1
      Page.showScore(s"Score: $score")
      showNext()
    } else {
      Page.showSummary(s"You scored $score!")
      Page.disableGameButtons()
      gameOn = false
    }
  }

  def showNext(): Unit = {
    val games = Random.shuffle(0 until data.size toList) take 4 map data

    Page.showGames(games)

    answer = Random.nextInt(4)

    Page.showQuestion(games(answer).name)
  }

  def share() = {
    println("Share")
  }
}
