package pwascalajs

import org.scalajs.dom
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.Element

import scala.scalajs.js
import scala.util.Random
import scalatags.JsDom.all._

object Page {
  def init(): Unit = {
    dom.window.addEventListener("online", { e: Any =>
      hideOfflineWarning()
    }, false)

    dom.window.addEventListener("offline", { e: Any =>
      showOfflineWarning()
    }, false)

    dom.document.body.appendChild(header(id := "header",
      h2("Board Game Game", Styles.title),
      div(id := "offline", Styles.offline, "OFFLINE"),
      Styles.header
    ).render)

    dom.document.body.appendChild(div(id := "question", Styles.question).render)

    def gameButton(i: String, index: Int) = div(
      id := i,
      onclick := { _: MouseEvent => Game.selected(index) },
      Styles.button
    )

    import PaperTags._

    dom.document.body.appendChild(div(
//      div(gameButton("nw", 0), gameButton("ne", 1), Styles.buttonRow),
//      div(gameButton("sw", 2), gameButton("se", 3), Styles.buttonRow),
      gameButton("nw", 0),
      gameButton("ne", 1),
      gameButton("sw", 2),
      gameButton("se", 3),
      div(
        id := "summary",
        div(id := "summaryText", Styles.summaryText),
        div(onclick := { _: MouseEvent => Game.resetGame() }, "TRY AGAIN", Styles.summaryButton),
//        div(onclick := { _: MouseEvent => Game.share() }, "SHARE", Styles.summaryButton),
        Styles.summary
      ),
      Styles.buttons
    ).render)

    hideSummary()

    dom.document.body.appendChild(div(id := "score", Styles.score).render)
  }

  def gameButtons: Seq[Element] = Seq("#nw","#ne","#sw","#se") map dom.document.querySelector

  def showGames(games: Seq[BoardGame]): Unit = {
    gameButtons zip games foreach {
      case (b: Div, g: BoardGame) =>
        b.style.setProperty("background-image", s"url(${g.img})")

        val xoff = Random.nextInt(Math.max(1, g.width - b.clientWidth))
        b.style.setProperty("background-position-x", s"-${xoff}px")

        val yoff = Random.nextInt(Math.max(1, g.height - b.clientHeight))
        b.style.setProperty("background-position-y", s"-${yoff}px")
    }
  }

  def showQuestion(question: String): Unit = {
    dom.document.querySelector("#question").asInstanceOf[js.Dynamic].innerHTML = question
  }

  def showScore(result: String): Unit = {
    dom.document.querySelector("#score").asInstanceOf[js.Dynamic].innerHTML = result
  }

  def offlineDiv: Element = dom.document.querySelector("#offline")

  def showOfflineWarning(): Unit = offlineDiv.classList.remove(Styles.hidden.name)

  def hideOfflineWarning(): Unit = offlineDiv.classList.add(Styles.hidden.name)

  def summaryDiv: Element = dom.document.querySelector("#summary")

  def showSummary(msg: String): Unit = {
    dom.document.querySelector("#summaryText").asInstanceOf[js.Dynamic].innerHTML = msg
    summaryDiv.classList.remove(Styles.hidden.name)
    summaryDiv.classList.remove(Styles.hidden.name)
  }

  def hideSummary(): Unit = summaryDiv.classList.add(Styles.hidden.name)

  def disableGameButtons(): Unit = gameButtons foreach {
    _.classList.add(Styles.greyed.name)
  }

  def enableGameButtons(): Unit = gameButtons foreach {
    _.classList.remove(Styles.greyed.name)
  }
}
