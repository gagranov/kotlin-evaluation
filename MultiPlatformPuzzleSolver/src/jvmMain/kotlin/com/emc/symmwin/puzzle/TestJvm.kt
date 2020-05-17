package com.emc.symmwin.puzzle

import com.emc.symmwin.puzzle.Solver.Board

internal const val SIZE = 4
internal const val MAX_QUEUE_SIZE = 100000

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

internal fun print(board: Board) {
  if (board.parent != null) {
    print(board.parent!!)
  }
  print("Step", board)
}

@kotlin.ExperimentalStdlibApi
fun main() {
  demo()
  val results = test(SIZE, MAX_QUEUE_SIZE, 9, 1000)
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
  val puzzle = Board.getBoard(SIZE, 9)
  print("Puzzle", puzzle)
  val board = Solver.solve(puzzle, MAX_QUEUE_SIZE)
  if (board != null)
    print(board)
}
