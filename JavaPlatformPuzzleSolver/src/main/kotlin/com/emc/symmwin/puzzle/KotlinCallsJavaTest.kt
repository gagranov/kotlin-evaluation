package com.emc.symmwin.puzzle

import com.emc.symmwin.puzzle.JavaSolver.Board
import kotlin.system.measureTimeMillis

private const val SIZE = 4

private fun print(board: Board) {
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

fun main() {
  var found = 0
  var notFound = 0
  val time = measureTimeMillis {
    repeat(1000) {
      val solution = JavaSolver.solve(Board.getBoard(SIZE, 9))
      if (solution == null) {
        println("No Solution Found!")
        ++notFound
      } else {
        val size = solution.size
        if (solution.toTypedArray()[size - 1].distanceFromSolution() == 0) {
          ++found
        } else {
          ++notFound
        }
      }
    }
  }
  System.out.printf(
      "Kotlin Calls Java: Elapsed time %d ms. Found=%d NotFound=%d: %f%% success\n",
      time,
      found,
      notFound,
      100.0 * found / (found + notFound)
  )
}
