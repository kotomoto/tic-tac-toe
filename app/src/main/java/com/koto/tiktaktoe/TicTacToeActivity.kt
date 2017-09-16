package com.koto.tiktaktoe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class TicTacToeActivity : AppCompatActivity(), TicTacToeView {

    var buttonGrid: ViewGroup? = null
    var endGameIndicator: TextView? = null
    var winnerLabel: TextView? = null

    val presenter: TicTacToePresenter = TicTacToePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        buttonGrid = findViewById(R.id.board) as ViewGroup?
        endGameIndicator = findViewById(R.id.endGameIndicator) as TextView?
        winnerLabel = findViewById(R.id.winnerPlayerLabel) as TextView?

        presenter.onCreate()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_restart -> {
                presenter.onRestart()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onCellClicked(v: View) {
        val button = v as Button
        val tag = button.tag.toString()
        val row = Integer.valueOf(tag.substring(0, 1))!!
        val col = Integer.valueOf(tag.substring(1, 2))!!
        Log.i("TicTacToeActivity", "Click Row: [$row,$col]")

        presenter.onButtonSelected(row, col)
    }

    override fun setButtonText(row: Int, col: Int, text: String) {
        val btn = buttonGrid?.findViewWithTag<Button>("" + row + col)
        btn?.text = text
    }

    override fun clearButtons() {
        (0..8).forEach { i -> (buttonGrid?.getChildAt(i) as Button).text = "" }
    }

    override fun showWinner(winningPlayerLabel: String) {
        winnerLabel?.text = winningPlayerLabel
        endGameIndicator?.visibility = View.VISIBLE
    }

    override fun clearWinnerDisplay() {
        endGameIndicator?.visibility = View.GONE
        winnerLabel?.text = ""
    }
}