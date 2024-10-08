package com.example.fitlogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.FITLOGAppTheme

class TrainingActivity : ComponentActivity() {
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

                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        //TODO: return to MainAcitivity
    }
}