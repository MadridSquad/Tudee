package com.washingtondcsquad.tudee.presentation.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.washingtondcsquad.tudee.presentation.design.TudeeColors
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle

val LocalTudeeColors = staticCompositionLocalOf { TudeeColors.light }
val LocalTudeeTextStyle = staticCompositionLocalOf { defaultTextStyle }

@Composable
fun TudeeTheme(
    useDarkTheme: Boolean ,
    content: @Composable () -> Unit
){
    val theme = if (!useDarkTheme) {
        TudeeColors.light
    } else {
        TudeeColors.dark
    }

    CompositionLocalProvider(
        LocalTudeeTextStyle provides defaultTextStyle,
        LocalTudeeColors provides theme
    ) {
        content()
    }

}