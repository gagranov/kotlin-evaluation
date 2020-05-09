package com.emc.symmwin.puzzle

import com.emc.symmwin.puzzle.Solver.Board
import kotlin.system.measureTimeMillis

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
  var found = 0
  var notFound = 0
  val time = measureTimeMillis {
    for (i in 1..1000) {
      val solution = Solver.solve(Board.getBoard(SIZE, 9))
      val size = solution.size
      if (solution[size - 1].distanceFromSolution() == 0) {
        ++found
        //System.out.println("Solution Found: " + size + " move(s)!");
      } else {
        ++notFound
        //System.out.println("Solution Not Found: " + size + " move(s), distance: " + solution.get(size - 1).distanceFromSolution());
      }
      // for (Board board : solution) print(board);
    }
  }
  System.out.printf(
    "Elapsed time %d ms. Found=%d NotFound=%d: %f%% success\n",
    time,
    found,
    notFound,
    100.0 * found / (found + notFound)
  )
}
