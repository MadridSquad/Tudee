package com.washingtondcsquad.tudee.presentation.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.washingtondcsquad.tudee.presentation.design.colors.DarkTudeeColors
import com.washingtondcsquad.tudee.presentation.design.colors.LightTudeeColors
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle

@Composable
fun TudeeTheme(
    useDarkTheme: Boolean ,
    content: @Composable () -> Unit
){
    val theme = if (!useDarkTheme) {
        LightTudeeColors
    } else {
        DarkTudeeColors
    }

    CompositionLocalProvider(
        LocalTudeeTextStyle provides defaultTextStyle,
        LocalTudeeColors provides theme
    ) {
        content()
    }

}

