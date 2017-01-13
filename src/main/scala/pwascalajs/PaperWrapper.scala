package pwascalajs

import org.scalajs.dom.raw

import scala.scalajs.js
import scalatags.JsDom._
import scalatags.jsdom.TagFactory

trait PaperTags extends Util with TagFactory {
  lazy val paperButton = typedTag[PaperButtonElement]("paper-button")
  lazy val ironIcon = typedTag[IronIconElement]("iron-icon")
}

trait PaperAttrs extends Util {
  lazy val icon = attr("icon")
}

object PaperTags extends Cap with PaperTags with PaperAttrs with Aggregate

@js.native
abstract class PaperButtonElement extends raw.HTMLElement

@js.native
abstract class IronIconElement extends raw.HTMLElement
