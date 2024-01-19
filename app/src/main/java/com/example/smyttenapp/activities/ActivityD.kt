package com.example.smyttenapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.smyttenapp.ui.CommonActivityScreen
import com.example.smyttenapp.viewModels.DViewModel

class ActivityD : ComponentActivity() {

    lateinit var viewModel: DViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DViewModel::class.java)
        val time = intent.getLongExtra(MainActivity.TIME, 0L)
        if (time > 0) {
            viewModel.timer(time)
        }
        setContent {
            CommonActivityScreen(
                title = "Activity D",
                showDialog = viewModel.showDialog,
                onClick = ::onBackPressed,
                onDismissRequest = { viewModel.showDialog = false }
            )
        }
    }
}

