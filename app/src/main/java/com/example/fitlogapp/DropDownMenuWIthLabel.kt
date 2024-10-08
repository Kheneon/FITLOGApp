package com.example.fitlogapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitlogapp.ui.theme.TextWhite
import com.example.fitlogapp.ui.theme.TextWhiteDarker


@Composable
fun DropdownMenuWithLabel(
    label: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    options: List<String>,
    fillWholeWidth: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier =
        Modifier.then(if(fillWholeWidth) Modifier.fillMaxWidth() else Modifier.wrapContentWidth()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text =label,
            modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 10.dp)
            .then(if(fillWholeWidth) Modifier.fillMaxWidth() else Modifier.wrapContentWidth()),
            color = TextWhite
        )
        Text(
            text = selectedOption,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { expanded = true }
                .padding(16.dp)
                .border(BorderStroke(1.dp, TextWhiteDarker))
                .padding(8.dp),
            color = TextWhite
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    modifier = Modifier
                )
            }
        }
    }
}