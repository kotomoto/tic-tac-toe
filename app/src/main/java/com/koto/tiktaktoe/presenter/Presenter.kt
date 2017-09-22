package com.koto.tiktaktoe.presenter

interface Presenter {
    fun onCreate()
    fun onResume()
    fun onPause()
    fun onDestroy()
}