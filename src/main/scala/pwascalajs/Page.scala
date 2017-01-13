package pwascalajs

import org.scalajs.dom
import org.scalajs.dom.raw.Element

import scalatags.JsDom.all._

object Page {
  def init(): Unit = {
    dom.window.addEventListener("online", { e: Any =>
      hideOfflineWarning()
      Games.load()
    }, false)

    dom.window.addEventListener("offline", { e: Any =>
      showOfflineWarning()
    }, false)

    import PaperTags._

    Seq(
      header(id := "header",
        h2("Board Game Game", Styles.title),
        ironIcon(
          id := "offline",
          div(Styles.offlineIcon),
          Styles.offline
        ),
        Styles.header
      ).render,

      div(id := "image", Styles.image).render,

      div(
        div(Styles.button),
        div(Styles.button),
        div(Styles.button),
        div(Styles.button),
        Styles.buttons
      ).render
    ) foreach dom.document.body.appendChild

    // check if the user is connected
    if (dom.window.navigator.onLine) {
      Games.load()
      hideOfflineWarning()
    } else {
      showOfflineWarning()
    }
  }

  def showOfflineWarning() {
    println("Showing offline warning")
    dom.document.querySelector("#offline").classList.remove(Styles.hidden.name)
//
//    // load html template informing the user they are offline
//    dom.ext.Ajax.get("./offline.html").onComplete {
//      case Success(xhr) =>
//        val offlineDiv = div(id := "offline", Styles.offline).render
//        offlineDiv.innerHTML = xhr.responseText
//        dom.document.body.appendChild(offlineDiv)
//      case Failure(_) => println(s"Failed to load offline message.")
//    }
  }

  def hideOfflineWarning() {
    println("Hiding offline warning")
    dom.document.querySelector("#offline").classList.add(Styles.hidden.name)
  }
}
