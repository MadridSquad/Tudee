package com.washingtondcsquad.tudee

import SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.washingtondcsquad.tudee.presentation.design.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(
                useDarkTheme = true,
            ) {
                SplashScreen(
                    title = "Tudee",
                    isDarkTheme = true
                )


            }
        }
    }
}

