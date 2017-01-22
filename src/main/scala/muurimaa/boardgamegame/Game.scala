package muurimaa.boardgamegame

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
  var data: Seq[BoardGame] = Seq()

  var answer: Int = 0
  var answerName: String = ""
  var round: Seq[BoardGame] = Seq()
  var gameOn: Boolean = false

  private def dataUrl = if(App.debug) "./public/data.json" else "./data.json"

  val lowQuotes = Seq(
    "Finding \"GAME\" is all about mind over matter.",
    "Maybe you should watch Netflix instead.",
    "Maybe you should play Overwatch instead.",
    "Try hard. Complain harder.",
    "You have succeeded to fail.",
    "Nobody can stop you from finding \"GAME\", except yourself.",
    "You should have chosen the one next to that.",
    "Tell me... Is not knowing \"GAME\" a profession or are you just gifted?",
    "You thought that was \"GAME\"? Really?",
    "I'd agree with you that was \"GAME\", but then we'd both be wrong.",
    "Have you tried trying harder?",
    "Scientists are trying to figure out how you didn't know that wasn't \"GAME\".",
    "Google is attempting to develop an AI that would know less than you.",
    "Zombies eat brains. You're safe."
  )

  val highQuotes = Seq(
    "Nice! Try again. Or not.",
    "Hey, that could've been \"GAME\". But it wasn't.",
    "Almost, but not quite.",
    "Better and better. Again!",
    "Rinse and repeat. You can do it!"
  )

  val perfectQuotes = Seq(
    "Perfection!",
    "Yes! Congrats.",
    "Indeed. *tips hat*",
    "You are pretty good at this.",
    "Damn you're good!"
  )

  def load(): Unit = dom.ext.Ajax.get(dataUrl) onComplete {
    case Success(xhr) =>
      data = JSON.parse(xhr.responseText).asInstanceOf[js.Array[BoardGame]].toSeq
      resetGame()

    case Failure(_) => println(s"Failed to load game data.")
  }

  def progress: Double = (data.size - round.size) / data.size.toDouble
  def score: String = s"${Math.round(progress*100)}%"

  def resetGame(): Unit = {
    println("Starting game")

    round = Random.shuffle(data)

    Page.hideSummary()
    Page.enableGameButtons()

    gameOn = true
    showNext()
  }

  def selected(idx: Int): Unit = if(gameOn) {
    if (idx == answer && round.nonEmpty) {
      showNext()
    } else {
      if(idx != answer) {
        round = round :+ data.find(_.name == answerName).get
      }

      Page.showProgress(score)
      Page.showQuestion(score)

      val quote = progress match {
        case p if p < 0.5 => Random.shuffle(lowQuotes).head
        case p if p < 0.99 => Random.shuffle(highQuotes).head
        case _ => Random.shuffle(perfectQuotes).head
      }

      Page.showSummary(quote.replace("GAME", answerName))
      Page.disableGameButtons()
      gameOn = false
    }
  }

  def showNext(): Unit = {
    val others = Random.shuffle(data filter { _.name != round.head.name }) take 3
    val games = Random.shuffle(others :+ round.head)

    answer = games indexWhere { _.name == round.head.name }
    answerName = round.head.name

    Page.showGames(games)
    Page.showQuestion(answerName)
    Page.showProgress(score)

    round = round.tail
  }
}
