package com.example.fitlogapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitlogapp.ui.theme.BasicBackground
import com.example.fitlogapp.ui.theme.TextWhite
import java.time.LocalDate

// TODO: add date selection

@Composable
fun CreateTraining(viewModel: AppViewModel, context: Context){
    val tDateYear = remember { mutableIntStateOf(LocalDate.now().year) }
    val tDateMonth = remember { mutableIntStateOf(LocalDate.now().monthValue) }
    val tDateDay = remember { mutableIntStateOf(LocalDate.now().dayOfMonth) }
    val tDateYearList = getIntList(tDateYear.intValue,2000)
    val tDateMonthList = getIntList(1,12)
    val tDateDayList = getIntList(1,31)
    val tTrainingType = remember { mutableStateOf("") }
    val tTypeList by viewModel.trainingTypeList.observeAsState(emptyList())
    Column(modifier = Modifier.fillMaxSize().fillMaxHeight()) {
        Row (
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            DropdownMenuWithLabel(
                label = "",
                selectedOption = tTrainingType.value,
                onOptionSelected = { tTrainingType.value = it },
                options = tTypeList/*,
                fillWholeWidth = true*/
            )
            Button(
                onClick = { createTrainingType(context,viewModel) },
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                colors = ButtonDefaults.buttonColors(containerColor = BasicBackground)
            ) {
                Text(
                    text = "+",
                    color = TextWhite,
                    fontSize = 25.sp)
            }

        }
        Row (
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            // TODO: better positioning
            DropdownMenuWithLabel(
                label = "Day",
                selectedOption = tDateDay.intValue.toString(),
                onOptionSelected = {tDateDay.intValue = it.toInt()},
                options = tDateDayList)
            DropdownMenuWithLabel(
                label = "Month",
                selectedOption = tDateMonth.intValue.toString(),
                onOptionSelected = {tDateMonth.intValue = it.toInt()},
                options = tDateMonthList)
            DropdownMenuWithLabel(
                label = "Year",
                selectedOption = tDateYear.intValue.toString(),
                onOptionSelected = {tDateYear.intValue = it.toInt()},
                options = tDateYearList)
        }
        Button(
            onClick = {
                //TODO: date validation
                var response = viewModel.addTraining(
                    tDateYear = tDateYear.intValue,
                    tDateMonth = tDateMonth.intValue,
                    tDateDay = tDateDay.intValue,
                    tType = tTrainingType.value)
                if(response.status == ResponseStatus.RS_FAILURE) {
                    showInfoDialog(
                        context = context,
                        title = "Training addition",
                        message = response.payload,
                        dismissButtonText = "Ok"
                    )
                }else{
                    val intent = Intent(context,TrainingActivity::class.java).apply {  }
                    context.startActivity(intent)
                }
                //(context as? Activity)?.finish() // returns to MainActivity

                      },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Training")
        }
    }
}

/**
 * Create MutableIntList in specified range
 *
 * Can go both from larger to smaller and from smaller to larger (number)
 *
 * @param from Index from, list starts with it
 * @param to Index to, list ends with it
 */
fun getIntList(from: Int, to: Int): List<String>{
    val intList = mutableListOf<String>()
    if(from < to) {
        for (i in from..to) {
            intList += i.toString()
        }
    }else{
        for (i in from downTo to) {
            intList += i.toString()
        }
    }
    return intList
}