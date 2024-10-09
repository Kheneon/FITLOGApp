package com.example.fitlogapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys =
    [ForeignKey(
        entity = DBTraining::class,
        parentColumns = arrayOf("trainingUID"),
        childColumns = arrayOf("training_id"),
        onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = DBExerciseType::class,
        parentColumns = arrayOf("exerciseTypeName"),
        childColumns = arrayOf("exercise_type_name"),
        onDelete = ForeignKey.CASCADE
    )])
data class DBExercise(
    @PrimaryKey(autoGenerate = true) val exerciseUID: Int = 0,
    @ColumnInfo(name = "exercise_type_name") val exerciseTypeName: String,
    @ColumnInfo(name = "training_id") val trainingUID : Int,
    @ColumnInfo(name = "number_of_series") val numOfSeries: Int = 0,
    @ColumnInfo(name = "number_of_repetition") val numOfRepetition: Int = 0
)