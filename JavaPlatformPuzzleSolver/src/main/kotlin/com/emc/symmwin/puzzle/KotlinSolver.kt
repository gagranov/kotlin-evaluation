package com.emc.symmwin.puzzle

import java.util.*
import kotlin.math.abs

object KotlinSolver {

  @JvmStatic
  fun solve(puzzle: Board?): Collection<Board?>? {
    val queue = ArrayDeque<Board?>()
    val visited = HashSet<Board?>()
    var count = 0
    queue.addLast(puzzle)
    visited.add(puzzle)
    while (queue.size > 0) {
      var board = queue.removeFirst()
      if (++count == 100000 || board!!.distanceFromSolution() == 0) {
        val solution = ArrayDeque<Board?>()
        while (board != null) {
          solution.addFirst(board)
          board = board.parent
        }
        return solution
      }
      for (move in board.possibleMoves()) {
        val nextBoard = Board(board).makeMove(move)
        if (visited.add(nextBoard)) queue.addLast(nextBoard)
      }
    }
    return null
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
      assert(number[row][column] == 0)
      number[row][column] = number[move.row][move.column]
      assert(number[row][column] > 0)
      row = move.row
      column = move.column
      number[row][column] = 0
      return this
    }

    fun number(row: Int, column: Int): Int {
      return number[row][column]
    }

    override fun equals(other: Any?): Boolean {
      if (other is Board) {
        for (row in 0 until size) for (column in 0 until size) if (number[row][column] != other.number[row][column]) return false
        return true
      }
      return false
    }

    override fun hashCode(): Int {
      return number.contentDeepHashCode()
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
        for (row in 0 until size) for (column in 0 until size) board.number[row][column] = row * size + column + 1
        board.row = size - 1
        board.column = size - 1
        board.number[size - 1][size - 1] = 0
        return board
      }

      @JvmStatic
      fun getBoard(size: Int, randomMoveCount: Int): Board {
        val visited = HashSet<Board>()
        var board = getBoard(size)
        val rand = Random()
        visited.add(board)
        var count = randomMoveCount
        while (count > 0) {
          val moves = board.possibleMoves()
          val nextBoard = Board(board).makeMove(moves[rand.nextInt(moves.size)])
          if (visited.add(nextBoard)) {
            --count
            board = nextBoard
          }
        }
        return board
      }
    }

  }
}