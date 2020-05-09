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

fun main(args: Array<String>) {
  var found = 0
  var notFound = 0
  val time = measureTimeMillis {
    for (i in 1..1000) {
      val solution = JavaSolver.solve(Board.getBoard(SIZE, 9))
      if (solution == null) println("No Solution Found!") else {
        val size = solution.size
        if (solution.toTypedArray()[size - 1].distanceFromSolution() == 0) {
          ++found
          //System.out.println("Solution Found: " + size + " move(s)!");
        } else {
          ++notFound
          //System.out.println("Solution Not Found: " + size + " move(s), distance: " + solution.get(size - 1).distanceFromSolution());
        }
        // for (Board board : solution) print(board);
      }
    }
  }
  System.out.printf("Elapsed time %d ms. Found=%d NotFound=%d: %f%% success\n", time, found, notFound, 100.0 * found / (found + notFound))
}
