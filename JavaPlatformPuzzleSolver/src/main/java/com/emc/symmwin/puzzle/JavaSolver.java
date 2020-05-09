package com.emc.symmwin.puzzle;

import java.util.*;

public class JavaSolver {

  private JavaSolver() {
  }

  private static class Move {
    private final int m_row;
    private final int m_column;

    private Move(int row, int column) {
      m_row = row;
      m_column = column;
    }
  }

  public static class Board /*implements Comparable<Board>*/ {
    private final int m_size;
    private final int[][] m_number;
    // location with no tile:
    private int m_row;
    private int m_column;
    private Board m_parent;

    private Board(int size) {
      m_size = size;
      m_number = new int[size][size];
    }

    private Board(Board board) {
      this(board.m_size);
      for (int row = 0; row < m_size; ++row)
        for (int column = 0; column < m_size; ++column)
          m_number[row][column] = board.m_number[row][column];
      m_row = board.m_row;
      m_column = board.m_column;
      m_parent = board;
    }

    private List<Move> getPossibleMoves() {
      ArrayList<Move> moves = new ArrayList<>();
      for (int row = m_row - 1; row <= m_row + 1; row += 2)
        if ((row >= 0) && (row < m_size))
          moves.add(new Move(row, m_column));
      for (int column = m_column - 1; column <= m_column + 1; column += 2)
        if ((column >= 0) && (column < m_size))
          moves.add(new Move(m_row, column));
      return moves;
    }

    private Board makeMove(Move move) {
      assert m_number[m_row][m_column] == 0;
      m_number[m_row][m_column] = m_number[move.m_row][move.m_column];
      assert m_number[m_row][m_column] > 0;
      m_row = move.m_row;
      m_column = move.m_column;
      m_number[m_row][m_column] = 0;
      return this;
    }

    public int number(int row, int column) {
      return m_number[row][column];
    }

    public static Board getBoard(int size) {
      Board board = new Board(size);
      for (int row = 0; row < size; ++row)
        for (int column = 0; column < size; ++column)
          board.m_number[row][column] = row * size + column + 1;
      board.m_row = size - 1;
      board.m_column = size - 1;
      board.m_number[size - 1][size - 1] = 0;
      return board;
    }

    public static Board getBoard(int size, int randomMoveCount) {
      Board board = getBoard(size);
      Random rand = new Random();
      while (randomMoveCount > 0) {
        --randomMoveCount;
        List<Move> moves = board.getPossibleMoves();
        board.makeMove(moves.get(rand.nextInt(moves.size())));
      }
      return board;
    }

    public int distanceFromSolution() {
      int distance = 0;
      int shouldBeRow, shouldBeColumn;

      for (int row = 0; row < m_size; ++row)
        for (int column = 0; column < m_size; ++column) {
          int number = m_number[row][column];
          if (number == 0) {
            shouldBeRow = m_size - 1;
            shouldBeColumn = m_size - 1;
          } else {
            --number;
            shouldBeRow = number / m_size;
            shouldBeColumn = number % m_size;
          }
          distance += Math.abs(row - shouldBeRow) + Math.abs(column - shouldBeColumn);
        }

      return distance;
    }

    public int depth() {
      Board board = this;
      int count = 1;
      while (board.m_parent != null) {
        board = board.m_parent;
        ++count;
      }
      return count;
    }

    public int compareTo(Board board) {
      int compare = depth() - board.depth();
      if (compare == 0)
        compare = distanceFromSolution() - board.distanceFromSolution();
      return compare;
    }
  }

  public static Collection<Board> solve(Board board) {
    ArrayDeque<Board> queue = new ArrayDeque<>();
    int count = 0;
    queue.addLast(board);
    while (queue.size() > 0) {
      board = queue.removeFirst();
      if ((++count == 100000) || (board.distanceFromSolution() == 0)) {
        ArrayDeque<Board> solution = new ArrayDeque<>();
        while (board != null) {
          solution.addFirst(board);
          board = board.m_parent;
        }
        return solution;
      }
      for (Move move : board.getPossibleMoves()) {
        queue.addLast(new Board(board).makeMove(move));
      }
    }
    return null;
  }
}
