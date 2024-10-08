package com.example.fitlogapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

fun showInfoDialog(context: Context, title: String, message: String, dismissButtonText: String = "Ok"){
    val alertDialog = AlertDialog.Builder(context)


    alertDialog.apply {
        //TODO: UI
        //setIcon(R.drawable.ic_hello)
        setTitle(title)
        setMessage(message)
        /*setPositiveButton("Positive") { _: DialogInterface?, _: Int ->
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
        }
        setNegativeButton("Negative") { _, _ ->
            Toast.makeText(context, "Negative", Toast.LENGTH_SHORT).show()
        }*/
        setNeutralButton(dismissButtonText) { _, _ ->
            //Toast.makeText(context, "Neutral", Toast.LENGTH_SHORT).show()
        }
        /*setOnDismissListener {
            Toast.makeText(context, "Hello!!!", Toast.LENGTH_SHORT).show()
        }*/


    }.create().show()
}