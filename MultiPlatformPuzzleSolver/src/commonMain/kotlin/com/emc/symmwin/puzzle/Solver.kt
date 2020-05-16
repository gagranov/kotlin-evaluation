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
      val move = Solver.solve(puzzle, maxQueueSize)
      move.applyTo(puzzle)
      if (puzzle.distanceFromSolution() == 0) {
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
  fun solve(puzzle: Board, maxQueueSize: Int): Move {
    val queue = ArrayDeque<Move>()
    var count = 0
    for (possibleMove in puzzle.possibleMoves(null)) {
      queue.addLast(possibleMove)
    }
    var move: Move
    do {
      move = queue.removeFirst()
      val board = Board(puzzle)
      move.applyTo(board)
      if (++count == maxQueueSize || board.distanceFromSolution() == 0) {
        break
      }
      for (possibleMove in board.possibleMoves(move)) {
        queue.addLast(possibleMove)
      }
    } while (queue.size > 0)
    return move
  }

  class Move(val row: Int, val column: Int, val parent: Move?) {

    internal fun applyTo(board: Board) {
      parent?.applyTo(board)
      board.makeMove(this)
    }
  }

  class Board private constructor(private val size: Int) {
    private val number: Array<IntArray> = Array(size) { IntArray(size) }

    // location with no tile:
    private var row = 0
    private var column = 0

    constructor(board: Board) : this(board.size) {
      for (row in 0 until size) for (column in 0 until size) number[row][column] = board.number[row][column]
      row = board.row
      column = board.column
    }

    fun possibleMoves(parent: Move?): List<Move> {
      val moves = ArrayList<Move>()
      var row = row - 1
      while (row <= this.row + 1) {
        if (row in 0 until size) moves.add(Move(row, column, parent))
        row += 2
      }
      var column = column - 1
      while (column <= this.column + 1) {
        if (column in 0 until size) moves.add(Move(this.row, column, parent))
        column += 2
      }
      return moves
    }

    fun makeMove(move: Move) {
      number[row][column] = number[move.row][move.column]
      row = move.row
      column = move.column
      number[row][column] = 0
    }

    fun tryMove(row: Int, column: Int): Boolean {
      for (possibleMove in possibleMoves(null)) {
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
        repeat(randomMoveCount) {
          val moves = board.possibleMoves(null)
          moves[rand.nextInt(moves.size)].applyTo(board)
        }
        return board
      }
    }

  }
}
