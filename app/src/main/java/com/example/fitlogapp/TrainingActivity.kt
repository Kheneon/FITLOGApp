package com.example.fitlogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.FITLOGAppTheme

class TrainingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appViewModel = ViewModelProvider(this)[AppViewModel::class.java]
        setContent {
            val trainingName = remember { mutableStateOf("") }
            LaunchedEffect(Unit) {
                trainingName.value = appViewModel.getTrainingName()
            }
            FITLOGAppTheme (
                darkTheme = true
            ){
                Surface(
                    modifier = Modifier
                        .background(BasicBackground)
                        .fillMaxSize()
                        .background(BasicBackground),
                    color = BasicBackground
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = trainingName.value,
                            modifier = Modifier.align(Alignment.CenterHorizontally))
                        Button(onClick = {}) { }
                        ExerciseList(viewModel = appViewModel)
                    }

                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        //TODO: return to MainAcitivity
    }
}