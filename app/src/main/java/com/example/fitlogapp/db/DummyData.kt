package com.example.fitlogapp.db

/**
 * Inserts Dummy data into Room Database of this app
 */
fun insertDummyData(dao: AppDao?){
    insertDummyDataTrainingType(dao)
    insertDummyDataExerciseType(dao)
    //insertDummyDataTraining(dao)
}

/**
 * Inserts default types of training into Room Database
 */
fun insertDummyDataTrainingType(dao: AppDao?){
    val defaultTypes : List<String> = listOf(
        "Back",
        "Chest",
        "Back + Biceps",
        "Chest + Triceps",
        "Legs",
        "Shoulders",
        "Core"
    )
    val defaultDescriptions : List<String> = listOf(
        "Back Muscles",
        "Chest Muscles",
        "Back and Biceps muscles",
        "Chest and Triceps muscles",
        "Leg muscles",
        "Shoulder muscles",
        "ABS and stabilization system"
    )
    for(i in 0..<defaultTypes.size){
        dao?.insertTrainingType(DBTrainingType(defaultTypes[i],defaultDescriptions[i]))
    }
}

fun insertDummyDataExerciseType(dao: AppDao?){
    val defaultTypes : List<String> = listOf(
        "Deadlift",
        "Benchpress",
        "Biceps curl"
    )
    /*val defaultDescriptions : List<String> = listOf( TODO: add basic type of exercises
        "",
        "",
        "Back and Biceps muscles",
        "Chest and Triceps muscles",
        "Leg muscles",
        "Shoulder muscles",
        "ABS and stabilization system"
    )*/
    for(i in 0..<defaultTypes.size){
        dao?.insertExerciseType(DBExerciseType(defaultTypes[i],"TODO"))
    }
}

fun insertDummyDataTraining(dao: AppDao?){
    for (i in 1..4) {
        dao?.insertTraining(
            DBTraining(
                trainingType = "Chest",
                numOfExercises = i,
                trainingDate = 19990101,
            )
        )
    }
}