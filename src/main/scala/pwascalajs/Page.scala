package pwascalajs

import org.scalajs.dom
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Page {
  def hideOfflineWarning() {
    println("Hiding offline warning")
    val e = dom.document.getElementById("offline")
    dom.document.body.removeChild(e)
  }

  def showOfflineWarning() {
    println("Showing offline warning")

    // load html template informing the user they are offline
    dom.ext.Ajax.get("./offline.html").onComplete {
      case Success(xhr) =>
        val e = dom.document.createElement("div")
        e.setAttribute("id", "offline")
        e.innerHTML = xhr.responseText
        dom.document.body.appendChild(e)
      case Failure(_) => println(s"Failed to load grid data.")
    }
  }
}
