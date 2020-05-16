package com.emc.symmwin.puzzle

import com.emc.symmwin.puzzle.Solver.Board

private const val SIZE = 4

private fun print(board: Board) {
  println("Distance " + board.distanceFromSolution() + ":")
  for (row in 0 until SIZE) {
    for (column in 0 until SIZE) {
      val number = board.number(row, column)
      var s: String
      s = if (number > 0)
        number.toString() else "  "
      if (s.length == 1)
        s = " $s"
      print("$s ")
    }
    print("\n")
  }
  println()
}

@kotlin.ExperimentalStdlibApi
fun main() {
  val results = test(SIZE, 100000, 9, 300)
  println("Elapsed time " + results.elapsedTime + " ms. Found=" + results.found + " NotFound=" + results.notFound + " success " + (100.0 * results.found / (results.found + results.notFound)) + "%")
}

