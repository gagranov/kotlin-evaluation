package com.emc.symmwin.puzzle

import kotlin.math.abs
import kotlin.random.Random

expect fun getSystemTimeInMillis(): Long

inline fun measureTimeMillis(block: () -> Unit): Long {
  val start = getSystemTimeInMillis()
  block()
  return getSystemTimeInMillis() - start
}

class TestResults(val elapsedTime: Long, val found: Int, val notFound: Int)

@kotlin.ExperimentalStdlibApi
fun test(boardSize: Int, maxQueueSize: Int, randomMoveCount: Int, repeatCount: Int): TestResults {
  var found = 0
  var notFound = 0
  val time = measureTimeMillis {
    repeat(repeatCount) {
      val puzzle = Solver.Board.getBoard(boardSize, randomMoveCount)
      if (Solver.solve(puzzle, maxQueueSize) != null) {
        ++found
      } else {
        ++notFound
      }
    }
  }
  return TestResults(time, found, notFound)
}

object Solver {

  @kotlin.ExperimentalStdlibApi
  fun solve(puzzle: Board, maxQueueSize: Int): Board? {
    val queue = ArrayDeque<Board>()
    val visited = HashSet<Board>()
    var count = 0
    queue.addLast(puzzle)
    visited.add(puzzle)
    while (queue.size > 0) {
      val board = queue.removeFirst()
      if (board.distanceFromSolution() == 0)
        return board
      if (++count == maxQueueSize)
        break
      for (move in board.possibleMoves()) {
        val nextBoard = Board(board).makeMove(move)
        if (visited.add(nextBoard)) queue.addLast(nextBoard)
      }
    }
    return null
  }

  class Move constructor(val row: Int, val column: Int)

  class Board private constructor(private val size: Int) {
    private val number: Array<IntArray> = Array(size) { IntArray(size) }

    // location with no tile:
    private var row = 0
    private var column = 0

    var parent: Board? = null

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

    fun tryMove(row: Int, column: Int): Boolean {
      for (possibleMove in possibleMoves()) {
        if ((possibleMove.row == row) && (possibleMove.column) == column) {
          makeMove(possibleMove)
          return true
        }
      }
      return false
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

    companion object {
      private fun getBoard(size: Int): Board {
        val board = Board(size)
        for (row in 0 until size) for (column in 0 until size) board.number[row][column] = row * size + column + 1
        board.row = size - 1
        board.column = size - 1
        board.number[size - 1][size - 1] = 0
        return board
      }

      fun getBoard(size: Int, randomMoveCount: Int): Board {
        val visited = HashSet<Board>()
        var board = getBoard(size)
        val rand = Random(getSystemTimeInMillis())
        visited.add(board)
        var count = randomMoveCount
        while (count > 0) {
          val moves = board.possibleMoves()
          val nextBoard = Board(board).makeMove(moves[rand.nextInt(moves.size)])
          if (visited.add(nextBoard)) {
            --count
            board = nextBoard
            board.parent = null;
          }
        }
        return board
      }
    }
  }
}
