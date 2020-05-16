package com.emc.symmwin.puzzle

import com.emc.symmwin.puzzle.Solver.Board
import com.emc.symmwin.puzzle.Solver.Move

internal const val SIZE = 4
internal const val MAX_QUEUE_SIZE = 10000000

internal fun print(text: String, board: Board) {
  println("$text: Distance " + board.distanceFromSolution() + ":")
  for (row in 0 until SIZE) {
    for (column in 0 until SIZE) {
      val number = board.number(row, column)
      var s: String
      s = if (number > 0) String.format("%2d", number) else "  "
      print("$s ")
    }
    print("\n")
  }
  println()
}

internal fun print(board: Board, move: Move) {
  if (move.parent != null) {
    print(board, move.parent)
  }
  board.makeMove(move)
  print("Blank was moved to row=${(move.row + 1)} column=${(move.column + 1)}", board)
}

@kotlin.ExperimentalStdlibApi
fun main() {
  demo()
  val results = test(SIZE, MAX_QUEUE_SIZE, 12, 3000)
  System.out.printf(
      "Elapsed time %d ms. Found=%d NotFound=%d: %f%% success\n",
      results.elapsedTime,
      results.found,
      results.notFound,
      100.0 * results.found / (results.found + results.notFound)
  )
}

@kotlin.ExperimentalStdlibApi
fun demo() {
  val puzzle = Board.getBoard(SIZE, 12)
  print("Puzzle", puzzle)
  val move = Solver.solve(puzzle, MAX_QUEUE_SIZE)
  var board = Board(puzzle)
  move.applyTo(board)
  print("Solution", board)
  board = Board(puzzle)
  print(board, move)
}
