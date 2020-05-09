package com.emc.symmwin.puzzle;

import java.util.Collection;

import static com.emc.symmwin.puzzle.JavaSolver.Board;

public class JavaTest {

  private final static int SIZE = 4;

  private static void print(Board board) {
    System.out.println("Distance " + board.distanceFromSolution() + ":");
    for (int row = 0; row < SIZE; ++row) {
      for (int column = 0; column < SIZE; ++column) {
        int number = board.number(row, column);
        String s;
        if (number > 0)
          s = String.format("%2d", number);
        else
          s = "  ";
        System.out.print(s + " ");
      }
      System.out.print("\n");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int found, notFound;
    found = 0;
    notFound = 0;

    long start = System.currentTimeMillis();
    for (int i = 0; i < 10000; ++i) {
      Collection<Board> solution = JavaSolver.solve(Board.getBoard(SIZE, 9));
      if (solution == null)
        System.out.println("No Solution Found!");
      else {
        int size = solution.size();
        if (solution.toArray(new Board[0])[size - 1].distanceFromSolution() == 0) {
          ++found;
          //System.out.println("Solution Found: " + size + " move(s)!");
        } else {
          ++notFound;
          //System.out.println("Solution Not Found: " + size + " move(s), distance: " + solution.get(size - 1).distanceFromSolution());
        }
        // for (Board board : solution) print(board);
      }
    }
    long stop = System.currentTimeMillis();
    System.out.printf("Elapsed time %d ms. Found=%d NotFound=%d: %f%% success\n", stop - start, found, notFound, (100.0 * found) / (found + notFound));
  }
}

