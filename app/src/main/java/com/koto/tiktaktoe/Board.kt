package com.koto.tiktaktoe

class Board {
    private var cells = Array(3, { Array<Cell?>(3, { _ -> Cell(Player.EMPTY) }) })

    private var winner: Player? = null
    private var state: GameState? = null
    private var currentTurn: Player = Player.EMPTY

    private enum class GameState {
        IN_PROGRESS, FINISHED
    }

    init {
        restart()
    }

    /**
     * Restart or start a new game, will clear the board and win status
     */
    fun restart() {
        clearCells()
        winner = null
        currentTurn = Player.X
        state = GameState.IN_PROGRESS
    }

    /**
     * Mark the current row for the player who's current turn it is.
     * @param row 0..2
     * @param col 0..2
     * @return the player that moved or Player.EMPTY if we did not move anything.
     */
    fun mark(row: Int, col: Int): Player {

        var playerThatMoved = Player.EMPTY

        if (isValid(row, col)) {
            cells[row][col]?.player = currentTurn
            playerThatMoved = currentTurn

            if (isWinningMoveByPlayer(currentTurn, row, col)) {
                state = GameState.FINISHED
                winner = currentTurn

            } else {
                // flip the current turn and continue
                flipCurrentTurn()
            }
        }

        return playerThatMoved
    }

    fun getWinner(): Player? = winner

    private fun clearCells() {
        cells = Array(3, { Array<Cell?>(3, { _ -> Cell(Player.EMPTY) }) })
    }

    private fun isValid(row: Int, col: Int): Boolean =
            when {
                state == GameState.FINISHED -> false
                isOutOfBounds(row) || isOutOfBounds(col) -> false
                isCellValueAlreadySet(row, col) -> false
                else -> true
            }

    private fun isOutOfBounds(idx: Int): Boolean = idx < 0 || idx > 2

    private fun isCellValueAlreadySet(row: Int, col: Int): Boolean = cells[row][col]?.player != Player.EMPTY

    /**
     * Algorithm adapted from http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
     * @param player
     * @param currentRow
     * @param currentCol
     * @return true if `player` who just played the move at the `currentRow`, `currentCol`
     * *              has a tic tac toe.
     */
    private fun isWinningMoveByPlayer(player: Player?, currentRow: Int, currentCol: Int): Boolean =
            cells[currentRow][0]?.player == player         // 3-in-the-row
                    && cells[currentRow][1]?.player == player
                    && cells[currentRow][2]?.player == player

                    || cells[0][currentCol]?.player == player      // 3-in-the-column
                    && cells[1][currentCol]?.player == player
                    && cells[2][currentCol]?.player == player

                    || currentRow == currentCol            // 3-in-the-diagonal
                    && cells[0][0]?.player == player
                    && cells[1][1]?.player == player
                    && cells[2][2]?.player == player

                    || currentRow + currentCol == 2    // 3-in-the-opposite-diagonal
                    && cells[0][2]?.player == player
                    && cells[1][1]?.player == player
                    && cells[2][0]?.player == player

    private fun flipCurrentTurn() {
        currentTurn = when (currentTurn) {
            Player.O -> Player.X
            Player.X -> Player.O
            else -> {
                Player.EMPTY
            }
        }
    }
}