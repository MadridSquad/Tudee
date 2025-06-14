package com.washingtondcsquad.tudee.presentation.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle

@Composable
fun TudeeTheme(
    useDarkTheme: Boolean ,
    content: @Composable () -> Unit
){
    if (useDarkTheme){ //todo check if dark theme is enabled
    }
    CompositionLocalProvider(
        LocalTudeeTextStyle provides defaultTextStyle,
    ) {
        content()
    }

}