package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import scala.scalajs.js

class JsAppController extends AppController {

  val jsController = window.jsUIController

  override def createAppView(): AppView = {
    new JsAppView()
  }

  override def createPuzzleView(gridSize: GridSize): PuzzleView = {
    new JsPuzzleView()
  }

}