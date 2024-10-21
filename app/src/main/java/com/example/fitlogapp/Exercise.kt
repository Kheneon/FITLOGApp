package com.example.fitlogapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitlogapp.db.DBExercise
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.TextWhite
import com.example.fitlogapp.ui.theme.TextWhiteDarker

@Composable
fun Exercise (exercise: DBExercise, viewModel: AppViewModel){
    //TODO: clickable logic
    //TODO: UI
    val numOfSeries = remember { mutableIntStateOf(exercise.numOfSeries) }
    val numOfRepetition = remember { mutableIntStateOf(exercise.numOfRepetition) }
    val addedWeight = remember { mutableDoubleStateOf(exercise.addedWeight) }
    val weightStep = 2.5
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(color = TextWhiteDarker)
                .padding(bottom = 1.dp)
                .background(color = BasicBackground)
                .padding(top = 10.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = exercise.exerciseTypeName,
                color = TextWhite
            )
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                Row(modifier = Modifier.wrapContentWidth().padding(end = 5.dp)) {
                    AddSubButton(label = "+", onClick = {
                        viewModel.exercisesAddNumOfSeries(exercise.exerciseUID)
                        numOfSeries.intValue += 1
                    })
                    Text(
                        text = numOfSeries.intValue.toString(),
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                    )
                    AddSubButton(label = "-", enabled= numOfSeries.intValue > 0, onClick = {
                        viewModel.exercisesSubNumOfSeries(exercise.exerciseUID)
                        numOfSeries.intValue -= 1
                    })
                }
                Row(modifier = Modifier.wrapContentWidth().padding(end = 5.dp)) {
                    AddSubButton(label = "+", onClick = {
                        viewModel.exercisesAddNumOfRepetition(exercise.exerciseUID)
                        numOfRepetition.intValue += 1
                    })
                    Text(
                        text = numOfRepetition.intValue.toString(),
                        modifier = Modifier.padding(5.dp)
                    )
                    AddSubButton(label = "-", enabled = numOfRepetition.intValue > 0, onClick = {
                        viewModel.exercisesSubNumOfRepetition(exercise.exerciseUID)
                        numOfRepetition.intValue -= 1
                    })
                }
                Row(modifier = Modifier.wrapContentWidth().padding(end = 5.dp)) {
                    AddSubButton(label = "+", onClick = {
                        viewModel.exercisesAddAddedWeight(exercise.exerciseUID)
                        addedWeight.doubleValue += weightStep
                    })
                    Text(
                        text = addedWeight.doubleValue.toString(), //TODO: default value based on previous exercise
                        modifier = Modifier.padding(5.dp)
                    )
                    AddSubButton(label = "-", enabled = addedWeight.doubleValue > 0, onClick = {
                        viewModel.exerciseSubAddedWeight(exercise.exerciseUID)
                        addedWeight.doubleValue -= weightStep
                    })
                }
                Text(
                    text = "X",
                    modifier = Modifier
                        .clickable {
                            viewModel.deleteExercise(exercise.exerciseUID)
                        }) // TODO: add modal on delete
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddSubButton(label: String = "+", onClick: () -> Unit = {}, enabled : Boolean = true){
    val roundedCornerVal = 6.dp
    Box(modifier = Modifier
        .width(25.dp)
        .clip(RoundedCornerShape(roundedCornerVal))
        .background(color = TextWhiteDarker)
        .padding(2.dp)
        .clip(RoundedCornerShape(roundedCornerVal-2.dp))
        .background(color = BasicBackground)
    ){
        Text(
            text = label,
            modifier = Modifier
                .align(Alignment.Center)
                .clickable(enabled = enabled) {
                    onClick()
                }
        )
    }
}