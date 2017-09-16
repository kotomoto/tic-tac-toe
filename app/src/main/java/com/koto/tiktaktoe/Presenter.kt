package com.koto.tiktaktoe

interface Presenter {
    fun onCreate()
    fun onResume()
    fun onPause()
    fun onDestroy()
}