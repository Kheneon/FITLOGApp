package com.example.fitlogapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBTrainingType(
    @PrimaryKey val trainingTypeName: String,
    @ColumnInfo(name = "training_type_note") val trainingTypeNote: String = "",
)