package com.example.fitlogapp

import androidx.collection.IntList
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlogapp.db.DBTraining
import com.example.fitlogapp.db.DBTrainingType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

class AppViewModel : ViewModel() {
    val appDao = MainApplication.appDB.appDao()

    val trainingList : LiveData<List<DBTraining>> = appDao.getAllTrainings()

    val trainingTypeList : LiveData<List<String>> = appDao.getAllTrainingTypes()

    fun addTraining( tDateYear : Int, tDateMonth : Int, tDateDay : Int, tType : String) : Response{
        if(!isDateValid(tDateYear,tDateMonth,tDateDay)){
            return Response(rStatus = ResponseStatus.RS_FAILURE, rPayload = "Invalid date")
        }
        // TODO: check if tTypeName is valid in DBTrainingType table


        viewModelScope.launch(Dispatchers.IO) {
            appDao.insertTraining(
                DBTraining(
                    numOfExercises = 0,
                    trainingDate = tDateYear*10000+tDateMonth*100+tDateDay,
                    trainingType = tType
                )
            )
        }
        return Response(rStatus = ResponseStatus.RS_SUCCESS, rPayload = "")
    }

    fun addTrainingType(name: String, note: String): Response{
        viewModelScope.launch(Dispatchers.IO) {
            appDao.insertTrainingType(
                DBTrainingType(trainingTypeName = name,  trainingTypeNote = note)
            )
        }
        return Response(rStatus = ResponseStatus.RS_SUCCESS, rPayload = "Training type successfully added")
    }

    /**
     * Check if date is valid in calendar
     */
    private fun isDateValid(year: Int, month: Int, day: Int): Boolean{
        if(day > 31 || day < 1){
            return false
        }
        val month31Days = listOf<Int>(1,3,5,7,8,10,12)
        if(!month31Days.contains(month) && day > 30){
            return false
        }else if(month == 2 && day > 28){
            if(year % 4 != 0 || day > 29){
                return false
            }
        }

        return true
    }
}