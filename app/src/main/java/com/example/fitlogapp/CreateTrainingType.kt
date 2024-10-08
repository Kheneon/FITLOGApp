package com.example.fitlogapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.EditText
import android.widget.LinearLayout

fun createTrainingType(context: Context, viewModel: AppViewModel){
    val alertDialog = AlertDialog.Builder(context)

    val layout = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(50, 40, 50, 10) // Nastavení paddingu pro prostor okolo polí
    }

    val newTrainingTypeName = EditText(context).apply {
        hint = "New training type"
    }

    val newTrainingTypeNote = EditText(context).apply {
        hint = "Note (optional)"
    }

    layout.addView(newTrainingTypeName)
    layout.addView(newTrainingTypeNote)

    alertDialog.apply {
        //TODO: UI
        setTitle("New type of training")
        setView(layout)
        setPositiveButton("Add") { _: DialogInterface?, _: Int ->
            viewModel.addTrainingType(newTrainingTypeName.text.toString(),newTrainingTypeNote.text.toString())
        }
        setNegativeButton("Cancel",null)
    }.create().show()
}