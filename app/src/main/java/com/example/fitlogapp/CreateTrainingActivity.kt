package com.example.fitlogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.FITLOGAppTheme

class CreateTrainingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appViewModel = ViewModelProvider(this)[AppViewModel::class.java]
        setContent {
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
                    Box(modifier = Modifier.fillMaxSize()){
                        CreateTraining(appViewModel, LocalContext.current)
                    }
                }
            }
        }
    }
}