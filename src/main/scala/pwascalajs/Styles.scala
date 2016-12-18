package pwascalajs

import scalatags.JsDom.all._
import scalatags.stylesheet._

object Styles extends StyleSheet {
  def body = cls(
    width := 100.pct,
    margin := 0.px
  )
}