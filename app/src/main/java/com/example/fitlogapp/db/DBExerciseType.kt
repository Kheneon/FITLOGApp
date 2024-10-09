package com.example.fitlogapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBExerciseType(
    @PrimaryKey val exerciseTypeName: String,
    @ColumnInfo(name = "exercise_type_note") val exerciseTypeNote : String
)