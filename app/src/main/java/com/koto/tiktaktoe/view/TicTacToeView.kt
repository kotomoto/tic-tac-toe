package com.koto.tiktaktoe.view

interface TicTacToeView {
    fun showWinner(winningPlayerLabel: String)
    fun clearWinnerDisplay()
    fun clearButtons()
    fun setButtonText(row: Int, col: Int, text: String)
}