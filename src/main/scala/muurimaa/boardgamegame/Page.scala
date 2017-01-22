package muurimaa.boardgamegame

import org.scalajs.dom
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.html.Div

import scala.scalajs.js
import scala.scalajs.js.Dynamic
import scala.util.Random
import scalatags.JsDom.all._

object Page {
  // Set up listeners and build the DOM
  def init(): Unit = {
    dom.window.addEventListener("online", { e: Any =>
      hideOfflineWarning()
    }, false)

    dom.window.addEventListener("offline", { e: Any =>
      showOfflineWarning()
    }, false)

    import PaperTags._

    dom.document.body.appendChild(header(id := "header",
      h2("Board Game Game", Styles.title),

      // An example how to use the wrapped Polymer components with Scalatags
      ironIcon(id := "offline", icon := "icons:cloud-off", Styles.offline),
      Styles.header
    ).render)

    dom.document.body.appendChild(div(
      div(id := "progress", Styles.progress),
      div(id := "question", Styles.question),
      Styles.questionContainer
    ).render)

    dom.document.body.appendChild(div(
      div(id := "nw", onclick := { _: MouseEvent => Game.selected(0) }, Styles.button),
      div(id := "ne", onclick := { _: MouseEvent => Game.selected(1) }, Styles.button),
      div(id := "sw", onclick := { _: MouseEvent => Game.selected(2) }, Styles.button),
      div(id := "se", onclick := { _: MouseEvent => Game.selected(3) }, Styles.button),

      div(
        id := "summary",
        div(id := "summaryText", Styles.summaryText),
        div(
          div(onclick := { _: MouseEvent => Game.resetGame() }, "TRY AGAIN", Styles.summaryButton),
          Styles.summaryButtons
        ),
        Styles.summary,
        Styles.hidden // This gets removed when a game ends
      ),
      Styles.buttons
    ).render)
  }

  def gameButtons: Seq[Dynamic] = Seq("#nw","#ne","#sw","#se") map $

  def showGames(games: Seq[BoardGame]): Unit = {
    def gameUrl(game: BoardGame) = if(App.debug) s"url(public/${game.img})" else s"url(${game.img})"

    gameButtons zip games foreach {
      case (b: Div, g: BoardGame) =>
        b.style.setProperty("background-image", gameUrl(g))

        val xoff = Random.nextInt(Math.max(1, g.width - b.clientWidth))
        b.style.setProperty("background-position-x", s"-${xoff}px")

        val yoff = Random.nextInt(Math.max(1, g.height - b.clientHeight))
        b.style.setProperty("background-position-y", s"-${yoff}px")
    }
  }

  def showQuestion(question: String): Unit = $("#question").innerText = s"$question"

  def showProgress(progress: String): Unit = $("#progress").style.setProperty("width", progress)

  def showSummary(msg: String): Unit = {
    $("#summaryText").innerHTML = msg
    $("#summary").classList.remove(Styles.hidden.name)
  }

  def hideSummary(): Unit = $("#summary").classList.add(Styles.hidden.name)

  def disableGameButtons(): Unit = gameButtons foreach {
    _.classList.add(Styles.greyed.name)
  }

  def enableGameButtons(): Unit = gameButtons foreach {
    _.classList.remove(Styles.greyed.name)
  }

  def showOfflineWarning(): Unit = $("#offline").classList.remove(Styles.hidden.name)

  def hideOfflineWarning(): Unit = $("#offline").classList.add(Styles.hidden.name)

  // Helper function
  private def $(q: String): Dynamic = dom.document.querySelector(q).asInstanceOf[js.Dynamic]
}
