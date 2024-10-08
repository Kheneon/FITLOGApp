package com.example.fitlogapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(foreignKeys = [ForeignKey(entity = DBTrainingType::class, parentColumns = arrayOf("trainingTypeName"), childColumns = arrayOf("training_type"), onDelete = ForeignKey.CASCADE)])
data class DBTraining(
    @PrimaryKey(autoGenerate = true) val trainingUID: Int = 0,
    @ColumnInfo(name = "num_of_exercise") val numOfExercises: Int,
    @ColumnInfo(name = "training_date") val trainingDate: Int,  // Stored as YYYYMMDD
    @ColumnInfo(name = "training_type") val trainingType: String
)