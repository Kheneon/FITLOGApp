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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitlogapp.db.DBExercise
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.TextWhiteDarker

@Composable
fun Exercise (exercise: DBExercise, viewModel: AppViewModel){
    //TODO: clickable logic
    //TODO: UI
    val numOfSeries = remember { mutableIntStateOf(exercise.numOfSeries) }
    val numOfRepetition = remember { mutableIntStateOf(exercise.numOfRepetition) }
    val addedWeight = remember { mutableDoubleStateOf(exercise.addedWeight) }
    val weightStep : Double = 2.5
    Row (){
        Text(
            text = exercise.exerciseTypeName,
            color = TextWhiteDarker
        )
        Row (modifier = Modifier.wrapContentWidth()){
            Text(
                text = "+",
                modifier = Modifier.clickable {
                    viewModel.exercisesAddNumOfSeries(exercise.exerciseUID)
                    numOfSeries.intValue += 1}.padding(5.dp))
            Text(
                text = numOfSeries.intValue.toString(),
                modifier = Modifier.padding(5.dp))
            Text(
                text = "-",
                modifier = Modifier
                    .clickable (enabled = numOfSeries.intValue > 0){
                        viewModel.exercisesSubNumOfSeries(exercise.exerciseUID)
                        numOfSeries.intValue -= 1
                    }.padding(5.dp))
        }
        Row (modifier = Modifier.wrapContentWidth()){
            Text(
                text = "+",
                modifier = Modifier
                    .clickable {
                        viewModel.exercisesAddNumOfRepetition(exercise.exerciseUID)
                        numOfRepetition.intValue += 1
                    }.padding(5.dp))
            Text(
                text = numOfRepetition.intValue.toString(),
                modifier = Modifier.padding(5.dp))
            Text(
                text = "-",
                modifier = Modifier
                    .clickable (enabled = numOfRepetition.intValue > 0){
                        viewModel.exercisesSubNumOfRepetition(exercise.exerciseUID)
                        numOfRepetition.intValue -= 1
                    }.padding(5.dp))
        }
        Row (modifier = Modifier.wrapContentWidth()){
            Text(
                text = "+",
                modifier = Modifier
                    .clickable {
                        viewModel.exercisesAddAddedWeight(exercise.exerciseUID)
                        addedWeight.doubleValue += weightStep
                    }.padding(5.dp))
            Text(
                text = addedWeight.doubleValue.toString(), //TODO: default value based on previous exercise
                modifier = Modifier.padding(5.dp))
            Text(
                text = "-",
                modifier = Modifier
                    .clickable (enabled = addedWeight.doubleValue > 0){
                        viewModel.exerciseSubAddedWeight(exercise.exerciseUID)
                        addedWeight.doubleValue -= weightStep
                    }.padding(5.dp))
        }
    }
}