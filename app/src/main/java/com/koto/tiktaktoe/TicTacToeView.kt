package com.koto.tiktaktoe

interface TicTacToeView {
    fun showWinner(winningPlayerLabel: String)
    fun clearWinnerDisplay()
    fun clearButtons()
    fun setButtonText(row: Int, col: Int, text: String)
}