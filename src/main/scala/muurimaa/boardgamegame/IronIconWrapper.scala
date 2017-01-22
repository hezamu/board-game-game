package muurimaa.boardgamegame

import org.scalajs.dom.raw

import scala.scalajs.js
import scalatags.JsDom._
import scalatags.jsdom.TagFactory

// Define the ironIcon tag
trait PaperTags extends Util with TagFactory {
  lazy val ironIcon = typedTag[IronIconElement]("iron-icon")
}

// Define the icon property for the new tag
trait PaperAttrs extends Util {
  lazy val icon = attr("icon")
}

object PaperTags extends Cap with PaperTags with PaperAttrs with Aggregate

@js.native
abstract class IronIconElement extends raw.HTMLElement

