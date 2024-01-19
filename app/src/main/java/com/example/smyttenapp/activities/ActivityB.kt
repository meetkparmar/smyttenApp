package com.example.smyttenapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.smyttenapp.ui.CommonActivityScreen
import com.example.smyttenapp.viewModels.BViewModel

class ActivityB : ComponentActivity() {

    lateinit var viewModel: BViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BViewModel::class.java)
        val time = intent.getLongExtra(MainActivity.TIME, 0L)
        if (time > 0) {
            viewModel.timer(time)
        }
        setContent {
            CommonActivityScreen(
                title = "Activity B",
                showDialog = viewModel.showDialog,
                onClick = ::onBackPressed,
                onDismissRequest = { viewModel.showDialog = false }
            )
        }
    }
}