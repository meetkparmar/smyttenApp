package com.example.smyttenapp.viewModels

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.smyttenapp.PRODUCT_DATA
import com.example.smyttenapp.models.ProductDataModel
import com.google.gson.Gson

class MainViewModel() : ViewModel() {

    var productData by mutableStateOf<ProductDataModel?>(value = null)
    var showDialog by mutableStateOf(value = false)
    var remainingTime by mutableStateOf(value = 1L)

    init {
        productData = Gson().fromJson(PRODUCT_DATA, ProductDataModel::class.java)
        timer()
    }

    private fun timer() {
        val timer = object : CountDownTimer(5000, 1000) {
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