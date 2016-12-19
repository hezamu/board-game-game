package pwascalajs

import org.scalajs.dom

import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import scalatags.JsDom.tags2.style

object App extends JSApp {
  def main(): Unit = {
    dom.document.head.appendChild(style(Styles.styleSheetText).render)
    dom.document.body.applyTags(Styles.body)

    ServiceWorker.init()

    dom.window.addEventListener("online", { e: Any =>
      // re-sync data with server
      println("You are online")
      Page.hideOfflineWarning()
      Arrivals.loadData()
    }, false)

    dom.window.addEventListener("offline", { e: Any =>
      // queue up events for server
      println("You are offline")
      Page.showOfflineWarning()
    }, false)

    // check if the user is connected
    if (dom.window.navigator.onLine) {
      Arrivals.loadData()
    } else {
      Page.showOfflineWarning()
    }
  }
}