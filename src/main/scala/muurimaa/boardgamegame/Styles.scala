package muurimaa.boardgamegame

import scalatags.JsDom.all._
import scalatags.generic.Style
import scalatags.stylesheet._

object Styles extends StyleSheet {
  def body = cls(
    margin := 0.px,
    fontFamily := "\"Helvetica Neue\", \"Calibri Light\", Roboto, sans-serif",
    Style("", "text-rendering") := "optimizeLegibility",
    display := "flex",
    flexDirection := "column",
    alignItems := "center",
    height := 100.pct,
    width := 100.pct
  )

  def title = cls(paddingLeft := 0.5.em)

  def header = cls(
    backgroundColor := "#29BDBB",
    height := "3em",
    boxShadow := "0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24)",
    color := "#fff",
    width := 100.pct,
    display := "flex",
    alignItems := "center",
    justifyContent := "space-between"
  )

  def offline = cls(
    paddingRight := 1.em,
    fontSize := "small",
    fontWeight := "300"
  )

  def hidden = cls(visibility := "hidden")

  def centerText = cls(
    textAlign := "center",
    fontSize := 24.px
  )

  def questionContainer = cls(
    width := 100.pct,
    height := 48.px,
    position := "relative"
  )

  def fullSize = cls(
    position := "absolute",
    height := 100.pct
  )

  def question = cls(
    centerText.splice,
    fullSize.splice,
    width := 100.pct,
    lineHeight := 48.px,
    fontSize := 30.px,
    fontWeight := "300"
  )

  def progress = cls(
    fullSize.splice,
    width := 0.pct,
    backgroundColor := "lightgreen"
  )

  def buttons = cls(
    display := "flex",
    flexDirection := "column",
    flexWrap := "wrap",
    position := "relative",
    width := 100.pct,
    maxWidth := 400.px,
    height := 380.px
  )

  def greyed = cls(opacity := "0.3")

  def button = cls(
    width := 50.pct,
    height := 50.pct,
    backgroundRepeat := "no-repeat",
    boxShadow := "0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24)"
  )

  def score = cls(
    centerText.splice,
    height := 48.px,
    lineHeight := 48.px,
    fontSize := 30.px,
    fontWeight := "300"
  )

  def summary = cls(
    position := "absolute",
    display := "inline-table",
    top := 0.pct,
    bottom := 0.pct,
    left := 0.pct,
    right := 0.pct,
    width := 100.pct,
    margin := "auto",
    backgroundColor := "#29BDBB",
    color := "white"
  )

  def summaryButtons = cls(
    display := "flex",
    justifyContent := "space-around",
    padding := "10px 0px 10px"
  )

  def summaryText = cls(
    fontSize := 18.px,
    fontWeight := "300",
    fontStyle := "italic",
    margin := "10px 40px 10px"
  )

  def summaryButton = cls(
    fontSize := 12.px,
    fontWeight := "400",
    paddingRight := 10.px
  )
}
