package com.example.fitlogapp

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.FITLOGAppTheme
import com.example.fitlogapp.ui.theme.TextWhite
import com.example.fitlogapp.ui.theme.TextWhiteDarker

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
                        Row (
                            modifier = Modifier
                                .background(color = TextWhiteDarker)
                                .padding(bottom = 2.dp)
                                .background(color = BasicBackground)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                                text = trainingName.value,
                                fontSize = 30.sp,
                                color = TextWhite
                            )
                        }
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