package com.nirwashh.android.viewmodeltest.viewmodel

import java.util.*

interface TimeTicker {
    fun start(callback: Callback, period: Long = 1000)
    fun stop()

    interface Callback {
        fun tick()
    }
}

class TimerTicker : TimeTicker {
    private var callback: TimeTicker.Callback? = null
    private var timer: Timer? = null
    private val timerTask
        get() = object : TimerTask() {
            override fun run() {
                callback?.tick()
            }
        }
    override fun start(callback: TimeTicker.Callback, period: Long) {
        this.callback = callback
        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask, 0, period)
    }

    override fun stop() {
        callback = null
        timer?.cancel()
        timer = null
    }
}