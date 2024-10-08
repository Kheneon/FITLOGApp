package com.example.fitlogapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.TextWhiteDarker
import com.example.fitlogapp.ui.theme.TextWhite
import androidx.compose.ui.unit.dp

@Composable
fun Training (trainingName: String, numberOfExercises: Int, date: String, modifier: Modifier = Modifier){
    Surface (
        color = BasicBackground,
        modifier = modifier
            .background(color = TextWhiteDarker)
            .padding(top = 2.dp)
            .background(color = BasicBackground)
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        Row (verticalAlignment = Alignment.CenterVertically){
            Icon(
                painter = painterResource(id = R.drawable.dumbell_com),
                contentDescription = "icon",
                tint = Color.Black,
                modifier = modifier
                    .padding(end = 5.dp)
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(4.dp),
                        color = TextWhiteDarker)
                    .padding(5.dp)
                    .width(40.dp)
                    .height(40.dp)
            )
            Column {
                Text(
                    text = trainingName,
                    modifier = modifier,
                    color = TextWhite
                )
                Text(
                    text = date,
                    modifier = modifier,
                    color = TextWhiteDarker
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$numberOfExercises",
                modifier = modifier.padding(end = 10.dp),
                color = TextWhite,
            )
        }
    }
}