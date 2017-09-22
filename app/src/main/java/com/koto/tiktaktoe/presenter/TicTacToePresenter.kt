package com.koto.tiktaktoe.presenter

import com.koto.tiktaktoe.view.TicTacToeView
import com.koto.tiktaktoe.model.Board
import com.koto.tiktaktoe.model.Player

class TicTacToePresenter(val view: TicTacToeView) : Presenter {

    var model: Board

    init {
        model = Board()
    }

    override fun onCreate() {
        model = Board()
    }

    override fun onResume() {}

    override fun onPause() {}

    override fun onDestroy() {}

    fun onButtonSelected(row: Int, col: Int) {
        val player: Player = model.mark(row, col)

        if (player == Player.EMPTY) {
            return
        }

        view.setButtonText(row, col, player.toString())

        if (model.getWinner() != null) {
            view.showWinner(player.toString())
        }
    }

    fun onRestart() {
        view.clearWinnerDisplay()
        view.clearButtons()
        model.restart()
    }
}