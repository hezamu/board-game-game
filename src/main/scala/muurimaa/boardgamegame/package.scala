package muurimaa

import muurimaa.boardgamegame.PaperButtonElement
import org.scalajs.dom.raw.Element

package object boardgamegame {
  implicit def elem2paperbutton(e: Element): PaperButtonElement = e.asInstanceOf[PaperButtonElement]
}