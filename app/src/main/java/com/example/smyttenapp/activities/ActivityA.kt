package com.example.smyttenapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.smyttenapp.activities.MainActivity.Companion.TIME
import com.example.smyttenapp.ui.CommonActivityScreen
import com.example.smyttenapp.viewModels.AViewModel

class ActivityA : ComponentActivity() {

    lateinit var viewModel: AViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AViewModel::class.java)
        val time = intent.getLongExtra(TIME, 0L)
        if (time > 0) {
            viewModel.timer(time)
        }
        setContent {
            CommonActivityScreen(
                title = "Activity A",
                showDialog = viewModel.showDialog,
                onClick = ::onBackPressed,
                onDismissRequest = { viewModel.showDialog = false }
            )
        }
    }
}