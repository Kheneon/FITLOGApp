package com.example.fitlogapp

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.fitlogapp.ui.theme.BasicBackground
import androidx.compose.ui.unit.dp
import com.example.fitlogapp.ui.theme.TextWhite

//TODO: default number of trainings shown
//TODO: onCreate resets itemsInList variable - loads only first trainings
//TODO: onclick on training, open training itself with exercises

@Composable
fun TrainingList(viewModel: AppViewModel, context: Context){
    // Variables
    var itemsInList by remember { mutableIntStateOf(15) } // How many trainings will be shown
    val scrollState = rememberScrollState() // Remember actual scroll state on reload

    val tList by viewModel.trainingList.observeAsState(emptyList())

    // Functions
    fun onClickButtonHandlerLoadMore() {
        itemsInList += 5
    }

    // To return
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ){
        tList.take(itemsInList).forEach{ dbTraining ->
            Training(
                trainingName = dbTraining.trainingType,
                numberOfExercises = dbTraining.numOfExercises,
                // TODO: remove this s***
                date = dbTraining.trainingDate.toString().subSequence(6,8).toString().toInt().toString()
                        +"."
                        +dbTraining.trainingDate.toString().subSequence(4,6).toString().toInt().toString()
                        +"."
                        +dbTraining.trainingDate.toString().subSequence(0,4).toString().toInt().toString(), // TODO: remove logic from UI - move to ViewModel
                modifier = Modifier,
                context = context,
                viewModel = viewModel,
                DBt = dbTraining
            )
        }

        // TODO: user wont click on "Load more", it will load automatically more on screen movement (when scrolled to bottom -> load more)
        var isRowClickalbe = tList.size > itemsInList
        Row(
            modifier = Modifier
                .clickable(onClick = {
                    if(isRowClickalbe){
                        onClickButtonHandlerLoadMore()
                    }},
                    enabled = isRowClickalbe
                )
                .fillMaxWidth()
                .background(color = BasicBackground)
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = if (isRowClickalbe)"Load more" else "No more trainings to load",
                color = TextWhite
            )
        }
    }
}