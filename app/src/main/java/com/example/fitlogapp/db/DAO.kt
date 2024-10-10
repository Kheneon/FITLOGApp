package com.example.fitlogapp.db
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    // Training Type
    @Insert
    fun insertTrainingType(trainingType: DBTrainingType)

    @Query("DELETE FROM DBTrainingType WHERE trainingTypeName = :name")
    suspend fun deleteTrainingTypeByName(name: String)

    @Query("SELECT DBTrainingType.trainingTypeName FROM DBTrainingType")
    fun getAllTrainingTypes(): LiveData<List<String>>

    @Query("SELECT 1 FROM DBTrainingType WHERE trainingTypeName = :name")
    suspend fun isTrainingTypeAlreadyUsed(name: String): Boolean

    // Training
    @Insert
    fun insertTraining(training: DBTraining)

    @Query("SELECT * FROM DBTraining WHERE trainingUID = :id")
    suspend fun getTrainingById(id: Int): DBTraining

    @Query("SELECT * FROM DBTraining ORDER BY DBTraining.training_date DESC")
    fun getAllTrainings(): LiveData<List<DBTraining>>

    @Query("DELETE FROM DBTraining WHERE trainingUID = :id")
    suspend fun deleteTrainingById(id: Int)

    @Query("SELECT DBTraining.trainingUID FROM DBTraining ORDER BY trainingUID DESC LIMIT 1")
    suspend fun getLatestTrainingID(): Int

    @Query("SELECT DBTraining.training_type FROM DBTraining WHERE trainingUID = :tid")
    suspend fun getTrainingName(tid: Int): String

    @Query("UPDATE DBTraining SET num_of_exercise = :newNumOfExercise WHERE trainingUID = :tid")
    fun updateTrainingNumOfExercise(tid: Int, newNumOfExercise: Int)

    // Exercise
    @Insert
    fun insertExercise(exercise: DBExercise)

    @Query("SELECT * FROM DBExercise WHERE training_id = :tid")
    fun getAllExercises(tid: Int): LiveData<List<DBExercise>>

    @Query("SELECT * FROM DBExercise WHERE DBExercise.training_id = :uid")
    fun getSpecificTrainingExercises(uid: Int): LiveData<List<DBExercise>>

    @Query("UPDATE DBExercise SET number_of_series = :numOfSeries WHERE exerciseUID = :eid")
    fun updateExerciseNumOfSeries(eid: Int, numOfSeries: Int)

    @Query("UPDATE DBExercise SET number_of_repetition = :numOfReps WHERE exerciseUID = :eid")
    fun updateExerciseNumOfReps(eid: Int, numOfReps: Int)

    @Query("UPDATE DBExercise SET added_weight = :addedWeight WHERE exerciseUID = :eid")
    fun updateExerciseAddedWeight(eid: Int, addedWeight: Double)

    @Query("SELECT * FROM DBExercise WHERE exerciseUID = :eid")
    suspend fun getSpecificExercise(eid: Int): DBExercise

    @Query("DELETE FROM DBExercise WHERE exerciseUID = :eid")
    fun deleteExerciseById(eid: Int)

    // Exercise Type
    @Insert
    fun insertExerciseType(exerciseType: DBExerciseType)

    @Query("SELECT * FROM DBExerciseType")
    fun getAllExerciseTypes(): LiveData<List<DBExerciseType>>

    @Query("SELECT exerciseTypeName FROM DBExerciseType")
    fun getAllExerciseTypeNames(): LiveData<List<String>>
}