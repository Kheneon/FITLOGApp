package com.example.fitlogapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBExercise(
    @PrimaryKey(autoGenerate = true) val exerciseUID: Int,
    @ColumnInfo(name = "exercise_name") val exerciseName: String?
)