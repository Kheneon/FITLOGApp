package com.example.fitlogapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.FITLOGAppTheme
import com.example.fitlogapp.ui.theme.successColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationContext.deleteDatabase("app-db")
        val appViewModel = ViewModelProvider(this)[AppViewModel::class.java]
        setContent {
            FITLOGAppTheme (
                darkTheme = true
            ){
                Surface(modifier = Modifier
                    .background(BasicBackground)
                    .fillMaxSize()
                    .background(BasicBackground),
                    color = BasicBackground
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TrainingList(appViewModel, LocalContext.current)
                        Button(
                            onClick = {
                                //appViewModel.addTraining(tName = )
                                val intent = Intent(this@MainActivity,CreateTrainingActivity::class.java).apply {  }
                                startActivity(intent)},
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                                .size(60.dp)
                                .border(BorderStroke(2.dp, Color.Black), shape = CircleShape),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = successColor
                            ),
                            shape = CircleShape,

                        ) {
                            Text(text = "+", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}
