package pwascalajs

import org.scalajs.dom

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scalatags.JsDom.all._

object Page {
  def init(): Unit = {
    dom.window.addEventListener("online", { e: Any =>
      hideOfflineWarning()
      Games.load()
    }, false)

    dom.window.addEventListener("offline", { e: Any =>
      // queue up events for server
      println("You are offline")
      showOfflineWarning()
    }, false)

    Seq(
      header(
        div(
          h3("arrivals", Styles.headerH3),
          Styles.content
        ),
        Styles.header
      ).render,

      div(id := "image", Styles.image).render,

      div(
        Styles.container
      ).render
    ) foreach dom.document.body.appendChild

    // check if the user is connected
    if (dom.window.navigator.onLine) {
      Games.load()
    } else {
      showOfflineWarning()
    }
  }

  def showOfflineWarning() {
    println("Showing offline warning")

    // load html template informing the user they are offline
    dom.ext.Ajax.get("./offline.html").onComplete {
      case Success(xhr) =>
        val offlineDiv = div(id := "offline", Styles.offline).render
        offlineDiv.innerHTML = xhr.responseText
        dom.document.body.appendChild(offlineDiv)
      case Failure(_) => println(s"Failed to load offline message.")
    }
  }

  def hideOfflineWarning() {
    println("Hiding offline warning")
    val e = dom.document.getElementById("offline")
    dom.document.body.removeChild(e)
  }
}
