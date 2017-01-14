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

    Page.init()

    if (dom.window.navigator.onLine) {
      Game.load()
      Page.hideOfflineWarning()
    } else {
      Page.showOfflineWarning()
    }
  }
}