package example

import js.Dynamic.{ global => g }

object SplidingPuzzleMainJs {
  def main(): Unit = {
    val paragraph = g.document.createElement("p")
    paragraph.innerHTML = "<strong>It works!</strong>"
    g.document.getElementById("playground").appendChild(paragraph)
  }
}
