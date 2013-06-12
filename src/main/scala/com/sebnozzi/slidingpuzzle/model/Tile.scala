package com.sebnozzi.slidingpuzzle.model

import scala.util.Random

class Tile(val game: Game, val initialPosition: Position) {

  private var _currentPosition = initialPosition
  private var _tileMovedCallback: Option[() => Unit] = None

  def currentPosition = _currentPosition

  def currentPosition_=(newPosition: Position) {
    _currentPosition = newPosition
    game.tileDidMove(this)
    if(_tileMovedCallback.isDefined)
      _tileMovedCallback.get()
  }

  def onTileMoved(callback: => Unit) {
    _tileMovedCallback = Some(callback _)
  }

  def swapPositionWith(other: Tile) {
    val previousPosition = this.currentPosition
    this.currentPosition = other.currentPosition
    other.currentPosition = previousPosition
  }

  def makeHidden() {
    game.setHiddenTileAt(this.currentPosition)
  }

  def makeVisible() {
    game.clearHiddenTile
  }

  def isAtInitialPosition = (currentPosition == initialPosition)

  def isAdjacentTo(other: Tile) = this.adjacentTiles.contains(other)

  def canMoveToEmptySlot = {
    if (game.hasHiddenTile)
      this.isAdjacentTo(game.hiddenTile)
    else
      false
  }

  def moveToEmptySlot() {
    if (canMoveToEmptySlot)
      swapPositionWith(game.hiddenTile)
  }

  def moveToInitialPosition() {
    currentPosition = initialPosition
  }

  def adjacentTiles: List[Tile] = {
    currentPosition.adjacentIn(game.positionsRect).map { pos => game.tileAt(pos) }
  }

  def randomAdjacentTile: Tile = {
    val tiles = adjacentTiles
    Random.shuffle(tiles).head
  }

  override def toString() = {
    val positionStr = "ini(%d, %d)|cur(%d, %d)".format(
      initialPosition.col,
      initialPosition.row,
      currentPosition.col,
      currentPosition.row)
    s"Tile($positionStr)"
  }
}