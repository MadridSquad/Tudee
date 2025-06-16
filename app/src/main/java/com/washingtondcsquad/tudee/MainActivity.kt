package com.washingtondcsquad.tudee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.components.CustomSwitchButton
import com.washingtondcsquad.tudee.presentation.design.theme.TudeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TudeeTheme(
                useDarkTheme = false ,
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

