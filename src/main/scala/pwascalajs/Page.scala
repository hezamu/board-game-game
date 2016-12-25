package pwascalajs

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom
import org.scalajs.dom.html.Element

import scala.util.{Failure, Success}
import scalatags.JsDom.all._

class Page {
  dom.window.addEventListener("online", { e: Any =>
    // re-sync data with server
    println("You are online")
    hideOfflineWarning()
    Arrivals.loadData()
  }, false)

  dom.window.addEventListener("offline", { e: Any =>
    // queue up events for server
    println("You are offline")
    showOfflineWarning()
  }, false)

  // check if the user is connected
  if (dom.window.navigator.onLine) {
    Arrivals.loadData()
  } else {
    showOfflineWarning()
  }

  def initialDOM(): Seq[Element] = {
    Seq(
      header(
        div(
          h3("Arrivals", Styles.headerH3),
          Styles.content
        ),
        Styles.header
      ).render,

      div(
        div(
          ul(id := "arrivalsList",
            li(
              span("Title", Styles.title),
              span("Status", Styles.status),
              span("Time", Styles.time),
              Styles.item
            ),
            Styles.list
          ),
          Styles.listContent),
        Styles.container
      ).render
    )
    //    <header>
    //      <div class="content">
    //        <h3>Arrivals</h3>
    //      </div>
    //    </header>
    //      <div class="container">
    //        <div id="main" class="content">
    //          <ul class="arrivals-list" data-bind="foreach: arrivals">
    //            <li class="item">
    //              <span class="title" data-bind="html: title"></span>
    //              <span class="status" data-bind="html: status"></span>
    //              <span class="time" data-bind="html: time"></span>
    //            </li>
    //          </ul>
    //        </div>
    //      </div>
  }

  def hideOfflineWarning() {
    println("Hiding offline warning")
    val e = dom.document.getElementById("offline")
    dom.document.body.removeChild(e)
  }

  def showOfflineWarning() {
    println("Showing offline warning")

    // load html template informing the user they are ofscafline
    dom.ext.Ajax.get("./offline.html").onComplete {
      case Success(xhr) =>
        val off = div(id := "offline", Styles.offline).render
        //        off.applyTags(Styles.offline)
        //        off.setAttribute("id", "offline")
        off.innerHTML = xhr.responseText
        dom.document.body.appendChild(off)

      case Failure(_) => println(s"Failed to load grid data.")
    }
  }
}
