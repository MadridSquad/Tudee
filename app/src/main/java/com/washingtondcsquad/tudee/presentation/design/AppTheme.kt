package com.washingtondcsquad.tudee.presentation.design

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import com.washingtondcsquad.tudee.presentation.design.colorStyle.TudeeColors
import com.washingtondcsquad.tudee.presentation.design.colorStyle.dark
import com.washingtondcsquad.tudee.presentation.design.colorStyle.light
import com.washingtondcsquad.tudee.presentation.design.textStyle.TudeeTextStyle
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle

@Stable
object AppTheme {

    val textStyle: TudeeTextStyle
        @Composable
        @ReadOnlyComposable
        get() = LocalTudeeTextStyle.current

    val colors: TudeeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalTudeeColors.current

    @Composable
    operator fun invoke(
        useDarkTheme: Boolean,
        content: @Composable () -> Unit
    ) {
        val colors = if (!useDarkTheme) {
            light
        } else {
            dark
        }

        CompositionLocalProvider(
            LocalTudeeTextStyle provides defaultTextStyle,
            LocalTudeeColors provides colors
        ) {
            content()
        }

    }

    private val LocalTudeeColors = staticCompositionLocalOf { light }
    private val LocalTudeeTextStyle = staticCompositionLocalOf { defaultTextStyle }
}



