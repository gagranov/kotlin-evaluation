package com.emc.symmwin.puzzle

import com.emc.symmwin.puzzle.Solver.Board

internal const val SIZE = 4

internal fun print(board: Board) {
  println("Distance " + board.distanceFromSolution() + ":")
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

@kotlin.ExperimentalStdlibApi
fun main() {
  val results = test(SIZE, 100000, 9, 1000)
  System.out.printf(
      "Elapsed time %d ms. Found=%d NotFound=%d: %f%% success\n",
      results.elapsedTime,
      results.found,
      results.notFound,
      100.0 * results.found / (results.found + results.notFound)
  )
}
