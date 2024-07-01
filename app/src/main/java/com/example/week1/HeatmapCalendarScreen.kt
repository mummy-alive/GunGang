package com.example.week1

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.week1.databinding.ExerciseTimeDialogBinding

@Composable
fun CalendarScreen() {
    var showDialog by remember { mutableStateOf(false) }
    Column {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("운동 시간 기록하기")
        }
    }

    if (showDialog) {
        ExerciseTimeDialog(onDismiss = { showDialog = false })
    } else {
        //lorem ipsum
    }

}

@Composable
fun ExerciseTimeDialog(onDismiss: () -> Unit) {

    val context = LocalContext.current
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.exercise_time_dialog, null).apply {
                findViewById<Button>(R.id.successButton).setOnClickListener {
                    val calendarView = findViewById<CalendarView>(R.id.calendarView)
                    val editTime = findViewById<EditText>(R.id.editTime)
                    val selectedDate = calendarView.date
                    val exerciseTime = editTime.text.toString()

                    // Process the input values (e.g., save them or use them in the app)
                    Toast.makeText(
                        context,
                        "Date: $selectedDate, Time: $exerciseTime",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                findViewById<Button>(R.id.declineButton).setOnClickListener {
                    Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendarScreen() {
    CalendarScreen()
}