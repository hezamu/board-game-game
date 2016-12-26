package pwascalajs

import scalatags.JsDom.all.{background, fontFamily, top, _}
import scalatags.generic.Style
import scalatags.stylesheet._

object Styles extends StyleSheet {
  def body = cls(
    width := 100.pct,
    margin := 0.px,
    fontFamily := "\"Helvetica Neue\", \"Calibri Light\", Roboto, sans-serif",
    Style("", "text-rendering") := "optimizeLegibility",
    Style("", "-webkit-font-smoothing") := "antialiased",
    letterSpacing := "0.02em"
  )

  def offline = cls(
    margin := 0.px,
    background := "#f00",
    color := "#fff",
    padding := "1em",
    textAlign := "center",
    boxShadow := "0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24)",
    position := "absolute",
    left := 0.px,
    right := 0.px,
    top := 0.px
  )

  def content = cls(
    maxWidth := "72em",
    marginLeft := "auto",
    marginRight := "auto"
  )

  def listContent = cls(
    content.splice,
    marginTop := "3em",
    width := "100%",
    position := "relative"
  )

  def container = cls(
    minHeight := "100vh",
    display := "flex",
    flexDirection := "column"
  )

  def header = cls(
    padding := "0 0.5em",
    backgroundColor := "#29BDBB",
    height := "3em",
    boxShadow := "0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24)",
    lineHeight := "3em",
    color := "#fff",
    Style("", "backface-visibility") := "hidden"
  )

  def headerH3 = cls(
    top := "0",
    margin := "0"
  )

  def list = cls(
    margin := "0",
    listStyle := "none",
    display := "flex",
    flexDirection := "column",
    transition := "opacity 0.2s ease-in-out",
    padding := "0 0.5em",
    Style("", "-webkit-margin-before") := "1em",
    Style("", "-webkit-margin-after") := "1em",
    Style("", "-webkit-margin-start") := "0px",
    Style("", "-webkit-margin-end") := "0px",
    Style("", "-webkit-padding-start") := "40px"
  )

  def item = cls(
    borderRadius := "4px",
    margin := "0.5em auto",
    padding := "1em",
    width := "100%",
    backgroundColor := "#fff",
    textAlign := "left",
    boxShadow := "0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24)",
    display := "list-item",
    boxSizing := "border-box"
  )

  def image = cls(
    width := 200.px,
    height := 200.px
  )
}
