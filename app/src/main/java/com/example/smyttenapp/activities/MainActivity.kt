package com.example.smyttenapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.smyttenapp.ui.MainActivityScreen
import com.example.smyttenapp.viewModels.MainViewModel

class MainActivity : ComponentActivity() {

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setContent {
            MainActivityScreen(
                viewModel = viewModel,
                onActivityButtonClick = ::onActivityButtonClick
            )
        }
    }

    private fun onActivityButtonClick(index: Int) {
        val intent = when (index) {
            0 -> Intent(this, ActivityA::class.java)

            1 -> Intent(this, ActivityB::class.java)

            2 -> Intent(this, ActivityC::class.java)

            3 -> Intent(this, ActivityD::class.java)

            4 -> Intent(this, ActivityE::class.java)

            else -> Intent(this, ActivityF::class.java)
        }
        if (viewModel.remainingTime > 1000L) {
            intent.putExtra(TIME, viewModel.remainingTime)
            viewModel.showDialog = false
        }
        startActivity(intent)
    }

    companion object {
        const val TIME = "TIME"
    }
}