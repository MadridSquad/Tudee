package com.washingtondcsquad.tudee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme
import com.washingtondcsquad.tudee.presentation.design.theme.TudeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TudeeTheme(
                useDarkTheme = false ,
            ) {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TudeeTheme(
        useDarkTheme = false,
    ){
        Text(
            text = "Hello World",


            style = AppTheme.textStyle.headLine.large


        )

    }
}