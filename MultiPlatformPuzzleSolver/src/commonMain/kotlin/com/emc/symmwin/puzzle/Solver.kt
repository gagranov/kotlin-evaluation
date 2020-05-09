package com.emc.symmwin.puzzle

import kotlin.math.abs
import kotlin.random.Random

expect fun getSystemTimeInMillis(): Long

object Solver {

  @kotlin.ExperimentalStdlibApi
  fun solve(puzzle: Board): List<Board> {
    val queue = ArrayDeque<Board>()
    val solution = ArrayDeque<Board>()
    var count = 0
    queue.addLast(puzzle)
    while (queue.size > 0) {
      var board = queue.removeFirst()
      if (++count == 100000 || board.distanceFromSolution() == 0) {
        while (true) {
          solution.addFirst(board)
          if (board.parent != null) {
            board = board.parent!!
          } else
            break;
        }
        break
      }
      for (move in board.possibleMoves()) {
        queue.add(Board(board).makeMove(move))
      }
    }
    return solution
  }

  class Move constructor(val row: Int, val column: Int)

  class Board /*implements Comparable<Board>*/ private constructor(private val size: Int) {
    private val number: Array<IntArray> = Array(size) { IntArray(size) }

    // location with no tile:
    private var row = 0
    private var column = 0
    internal var parent: Board? = null

    constructor(board: Board?) : this(board!!.size) {
      for (row in 0 until size) for (column in 0 until size) number[row][column] = board.number[row][column]
      row = board.row
      column = board.column
      parent = board
    }

    fun possibleMoves(): List<Move> {
      val moves = ArrayList<Move>()
      var row = row - 1
      while (row <= this.row + 1) {
        if (row in 0 until size) moves.add(Move(row, column))
        row += 2
      }
      var column = column - 1
      while (column <= this.column + 1) {
        if (column in 0 until size) moves.add(Move(this.row, column))
        column += 2
      }
      return moves
    }

    fun makeMove(move: Move): Board {
      number[row][column] = number[move.row][move.column]
      row = move.row
      column = move.column
      number[row][column] = 0
      return this
    }

    fun number(row: Int, column: Int): Int {
      return number[row][column]
    }

    fun distanceFromSolution(): Int {
      var distance = 0
      var shouldBeRow: Int
      var shouldBeColumn: Int
      for (row in 0 until size) for (column in 0 until size) {
        var number = number[row][column]
        if (number == 0) {
          shouldBeRow = size - 1
          shouldBeColumn = size - 1
        } else {
          --number
          shouldBeRow = number / size
          shouldBeColumn = number % size
        }
        distance += abs(row - shouldBeRow) + abs(column - shouldBeColumn)
      }
      return distance
    }

    private fun depth(): Int {
      var board: Board? = this
      var count = 1
      while (board!!.parent != null) {
        board = board.parent
        ++count
      }
      return count
    }

    operator fun compareTo(board: Board): Int {
      var compare = depth() - board.depth()
      if (compare == 0) compare = distanceFromSolution() - board.distanceFromSolution()
      return compare
    }

    companion object {

      private fun getBoard(size: Int): Board {
        val board = Board(size)
        for (row in 0 until size) for (column in 0 until size) board.number[row][column] =
          row * size + column + 1
        board.row = size - 1
        board.column = size - 1
        board.number[size - 1][size - 1] = 0
        return board
      }

      fun getBoard(size: Int, randomMoveCount: Int): Board {
        val board = getBoard(size)
        val rand = Random(getSystemTimeInMillis())
        for (randomMove in 0 until randomMoveCount) {
          val moves = board.possibleMoves()
          board.makeMove(moves[rand.nextInt(moves.size)])
        }
        return board
      }
    }

  }
}