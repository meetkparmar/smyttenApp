package com.example.smyttenapp.viewModels

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DViewModel() : ViewModel() {

    var showDialog by mutableStateOf(value = false)
    var remainingTime by mutableStateOf(value = 1L)

    fun timer(milisInFuture: Long) {
        val timer = object : CountDownTimer(milisInFuture, 1000) {
            override fun onTick(time: Long) {
                remainingTime = time
            }

            override fun onFinish() {
                showDialog = true
            }
        }
        timer.start()
    }
}