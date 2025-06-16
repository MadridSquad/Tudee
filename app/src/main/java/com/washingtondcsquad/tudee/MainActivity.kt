package com.washingtondcsquad.tudee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.washingtondcsquad.tudee.presentation.design.theme.TudeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(
                useDarkTheme = false,
            ) {

                var isDark by remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CustomSwitchButton(
                        switchPadding = 2.dp,
                        buttonWidth = 64.dp,
                        buttonHeight = 36.dp,
                        value = isDark,
                        onToggle = { isDark = it }
                    )
                }

            }
        }
    }
}

