package pwascalajs

import org.scalajs.dom.document
import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import scalatags.JsDom.tags2.style

object App extends JSApp {
  def main(): Unit = {
    document.head.appendChild(style(Styles.styleSheetText).render)
    document.body.applyTags(Styles.body)

    document.body.appendChild(div("Hello, world!").render)
  }
}