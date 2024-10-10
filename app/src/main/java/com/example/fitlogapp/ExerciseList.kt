package com.example.fitlogapp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ExerciseList(viewModel: AppViewModel){
    val eList by viewModel.exerciseList.observeAsState(emptyList())
    val possibleExercises by viewModel.listOfAllExercises.observeAsState(emptyList())
    val defaultChooseTrainingString = "Choose new exercise"
    val addExercise = remember { mutableStateOf(defaultChooseTrainingString) }
    Column {
        eList.forEach(){exercise ->
            Exercise(exercise,viewModel)
        }
        DropdownMenuWithLabel(
            label = "Add exercise into your training",
            selectedOption = addExercise.value,
            onOptionSelected = {
                addExercise.value = it
                viewModel.addExercise(eTypeName = addExercise.value)
                addExercise.value = defaultChooseTrainingString
                },
            options = possibleExercises)
    }
}