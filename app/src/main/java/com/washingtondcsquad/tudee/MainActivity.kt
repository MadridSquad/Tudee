package com.washingtondcsquad.tudee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.screens.add_task.AddNewTaskScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(
                useDarkTheme = false,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                    .fillMaxSize())
                {
                    AddNewTaskScreen()
                }
            }
        }
    }
}


