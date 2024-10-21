package com.example.fitlogapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitlogapp.ui.theme.TextWhite
import com.example.fitlogapp.ui.theme.TextWhiteDarker

@Composable
fun ExerciseList(viewModel: AppViewModel){
    val eList by viewModel.exerciseList.observeAsState(emptyList())
    val possibleExercises by viewModel.listOfAllExercises.observeAsState(emptyList())
    val defaultChooseTrainingString = "Add exercise"
    val addExercise = remember { mutableStateOf(defaultChooseTrainingString) }
    Column (modifier = Modifier){
        eList.forEach{exercise ->
            Exercise(exercise,viewModel)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            DropdownMenuWithLabel(
                label = "",
                selectedOption = addExercise.value,
                onOptionSelected = {
                    addExercise.value = it
                    viewModel.addExercise(eTypeName = addExercise.value)
                    addExercise.value = defaultChooseTrainingString
                },
                options = possibleExercises
            )
            Text(
                text = "Create exercise",
                modifier = Modifier
                    .clickable {  }
                    .padding(16.dp)
                    .border(BorderStroke(1.dp, TextWhiteDarker))
                    .padding(8.dp),
                color = TextWhite
            )
        }
    }
}