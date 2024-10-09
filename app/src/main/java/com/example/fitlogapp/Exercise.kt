package com.example.fitlogapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitlogapp.db.DBExercise
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.TextWhiteDarker

@Composable
fun Exercise (exercise: DBExercise){
    Row (){
        Text(
            text = exercise.exerciseTypeName,
            color = TextWhiteDarker
        )
        Row (modifier = Modifier.wrapContentWidth()){
            Text(
                text = "+",
                modifier = Modifier.clickable {  }.padding(5.dp))
            Text(
                text = exercise.numOfSeries.toString(),
                modifier = Modifier.padding(5.dp))
            Text(
                text = "-",
                modifier = Modifier.clickable {  }.padding(5.dp))
        }
        Row (modifier = Modifier.wrapContentWidth()){
            Text(
                text = "+",
                modifier = Modifier.clickable {  }.padding(5.dp))
            Text(
                text = exercise.numOfRepetition.toString(),
                modifier = Modifier.padding(5.dp))
            Text(
                text = "-",
                modifier = Modifier.clickable {  }.padding(5.dp))
        }
    }

}