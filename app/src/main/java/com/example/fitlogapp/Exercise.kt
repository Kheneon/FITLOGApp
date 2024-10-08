package com.example.fitlogapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.TextWhiteDarker

@Composable
fun Exercise (name: String, modifier: Modifier = Modifier){
    Surface (color = BasicBackground) {
        Row {
            Text(
                text = "Hello $name!",
                modifier = modifier.fillMaxWidth(),
                color = TextWhiteDarker
            )
            Text(
                text = "Ahoj",
                modifier = modifier,
                color = TextWhiteDarker
            )
        }

    }
}