import org.scalajs.dom.raw.Element
import pwascalajs.PaperButtonElement

package object components {
  implicit def elem2paperbutton(e: Element): PaperButtonElement = e.asInstanceOf[PaperButtonElement]
}