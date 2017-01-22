package muurimaa.boardgamegame

import org.scalajs.dom

import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import scalatags.JsDom.tags2.style

object App extends JSApp {

  def debug: Boolean = dom.window.location.href.indexOf("index-dev.html") > 0

  def main(): Unit = {
    if(debug) println("Debugging")

    dom.document.head.appendChild(style(Styles.styleSheetText).render)
    dom.document.body.applyTags(Styles.body)

    if(debug) println("Initializing service worker")

    ServiceWorker.init()

    if(debug) println("Building DOM")

    Page.init()

    if (dom.window.navigator.onLine) {
      if(debug) println("Loading assets")

      Game.load()
      Page.hideOfflineWarning()
    } else {
      Page.showOfflineWarning()
    }
  }
}