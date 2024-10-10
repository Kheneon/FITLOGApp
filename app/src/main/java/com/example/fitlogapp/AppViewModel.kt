package com.example.fitlogapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlogapp.db.DBExercise
import com.example.fitlogapp.db.DBExerciseType
import com.example.fitlogapp.db.DBTraining
import com.example.fitlogapp.db.DBTrainingType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

class AppViewModel: ViewModel() {
    val appDao = MainApplication.appDB.appDao()

    val trainingList : LiveData<List<DBTraining>> = appDao.getAllTrainings()

    val listOfAllExercises : LiveData<List<String>> = appDao.getAllExerciseTypeNames()

    private val _exerciseList = MutableLiveData<List<DBExercise>>()
    var exerciseList : LiveData<List<DBExercise>> = appDao.getAllExercises(MainApplication.actualTrainingID)

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
            MainApplication.actualTrainingID = appDao.getLatestTrainingID()
            println(MainApplication.actualTrainingID)
        }
        return Response(rStatus = ResponseStatus.RS_SUCCESS, rPayload = "")
    }

    suspend fun getTrainingName(tid: Int = MainApplication.actualTrainingID): String{
        return appDao.getTrainingName(tid)
    }

    /**
     * Adds training type into database
     *
     * @param name New training type name
     * @param note Training type note
     *
     * @return Response class with result of DB operation
     */
    fun addTrainingType(name: String, note: String): Response{
        if(name == ""){
            return Response(rStatus = ResponseStatus.RS_FAILURE, rPayload = "Training type name is mandatory")
        }
        viewModelScope.launch(Dispatchers.IO) {
            if(!appDao.isTrainingTypeAlreadyUsed(name)) {
                appDao.insertTrainingType(
                    DBTrainingType(trainingTypeName = name, trainingTypeNote = note)
                )
            }
        }
        return Response(rStatus = ResponseStatus.RS_SUCCESS, rPayload = "Training type successfully added")
    }

    fun addExercise(tid: Int = MainApplication.actualTrainingID, eTypeName: String){
        // TODO: check exercise type validity
        viewModelScope.launch(Dispatchers.IO){
            appDao.insertExercise(
                DBExercise(
                    exerciseTypeName = eTypeName,
                    trainingUID = tid
                )
            )
        }
    }

    fun getTrainingExercises(tid: Int = MainApplication.actualTrainingID){
        viewModelScope.launch(Dispatchers.IO){
            appDao.getAllExercises(tid).observeForever { exercises ->
                _exerciseList.postValue(exercises)
            }
        }
    }

    fun openTraining(tid: Int){
        MainApplication.actualTrainingID = tid
    }

    fun exercisesAddNumOfSeries(eid: Int): Response{
        viewModelScope.launch(Dispatchers.IO) {
            var exercise = appDao.getSpecificExercise(eid)
            appDao.updateExerciseNumOfSeries(eid,exercise.numOfSeries + 1)
        }
        return Response(rStatus = ResponseStatus.RS_SUCCESS)
    }

    fun exercisesSubNumOfSeries(eid: Int): Response{
        viewModelScope.launch(Dispatchers.IO){
            var exercise = appDao.getSpecificExercise(eid)
            if (exercise.numOfSeries > 0) {
                appDao.updateExerciseNumOfSeries(eid, exercise.numOfSeries - 1)
            }
        }
        return Response(ResponseStatus.RS_SUCCESS)
    }

    fun exercisesAddNumOfRepetition(eid: Int): Response{
        viewModelScope.launch(Dispatchers.IO) {
            var exercise = appDao.getSpecificExercise(eid)
            appDao.updateExerciseNumOfReps(eid,exercise.numOfRepetition + 1)
        }
        return Response(rStatus = ResponseStatus.RS_SUCCESS)
    }

    fun exercisesSubNumOfRepetition(eid: Int): Response{
        viewModelScope.launch(Dispatchers.IO){
            var exercise = appDao.getSpecificExercise(eid)
            if (exercise.numOfRepetition > 0) {
                appDao.updateExerciseNumOfReps(eid, exercise.numOfRepetition - 1)
            }
        }
        return Response(ResponseStatus.RS_SUCCESS)
    }

    fun exercisesAddAddedWeight(eid: Int): Response{
        viewModelScope.launch(Dispatchers.IO) {
            var exercise = appDao.getSpecificExercise(eid)
            appDao.updateExerciseAddedWeight(eid,exercise.addedWeight + 2.5)
        }
        return Response(rStatus = ResponseStatus.RS_SUCCESS)
    }

    fun exerciseSubAddedWeight(eid: Int): Response{
        viewModelScope.launch(Dispatchers.IO){
            var exercise = appDao.getSpecificExercise(eid)
            if (exercise.addedWeight > 0.0) {
                appDao.updateExerciseAddedWeight(eid, exercise.addedWeight - 2.5)
            }
        }
        return Response(ResponseStatus.RS_SUCCESS)
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