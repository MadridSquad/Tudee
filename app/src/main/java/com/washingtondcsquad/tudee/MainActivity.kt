package com.washingtondcsquad.tudee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.deletetask.ConfirmDeleteTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(
                useDarkTheme = false,
            ) {


                var isSheet by rememberSaveable { mutableStateOf(false) }

                Box {
                    Button(
                        onClick = {
                            isSheet = true
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Open Bottom Sheet")
                    }

                    if (isSheet) {
                        ConfirmDeleteTask(
                            onDismiss = { isSheet = false },
                            deleteOnClick = {},
                            cancelOnClick = {}
                        )
                    }
                }

            }
        }
    }
}

