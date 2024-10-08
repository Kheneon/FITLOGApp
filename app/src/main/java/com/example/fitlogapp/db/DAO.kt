package com.example.fitlogapp.db
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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



    // Training
    @Insert
    fun insertTraining(training: DBTraining)

    @Query("SELECT * FROM DBTraining WHERE trainingUID = :id")
    fun getTrainingById(id: Int): Flow<DBTraining?>

    @Query("SELECT * FROM DBTraining ORDER BY DBTraining.training_date DESC")
    fun getAllTrainings(): LiveData<List<DBTraining>>

    @Query("DELETE FROM DBTraining WHERE trainingUID = :id")
    suspend fun deleteTrainingById(id: Int)

    @Insert
    fun insertExercise(exercise: DBExercise)
}