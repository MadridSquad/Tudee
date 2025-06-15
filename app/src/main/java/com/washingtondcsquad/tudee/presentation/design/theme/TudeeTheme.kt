package com.washingtondcsquad.tudee.presentation.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.washingtondcsquad.tudee.presentation.design.colors.darkTudeeColors
import com.washingtondcsquad.tudee.presentation.design.colors.lightTudeeColors
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle

val LocalTudeeColors = staticCompositionLocalOf { lightTudeeColors }
val LocalTudeeTextStyle = staticCompositionLocalOf { defaultTextStyle }

@Composable
fun TudeeTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme() ,
    content: @Composable () -> Unit
){
    val theme = if (!useDarkTheme) {
        lightTudeeColors
    } else {
        darkTudeeColors
    }

    CompositionLocalProvider(
        LocalTudeeTextStyle provides defaultTextStyle,
        LocalTudeeColors provides theme
    ) {
        content()
    }

}