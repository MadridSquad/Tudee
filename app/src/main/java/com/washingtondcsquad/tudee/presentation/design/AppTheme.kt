package com.washingtondcsquad.tudee.presentation.design

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
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

    val sizes: Sizes
        @Composable
        @ReadOnlyComposable
        get() = LocalSizes.current


    @Composable
    operator fun invoke(
        useDarkTheme: Boolean,
        content: @Composable () -> Unit
    ) {
        val colors = if (!useDarkTheme) {
            TudeeColors.light
        } else {
            TudeeColors.dark
        }

        CompositionLocalProvider(
            LocalTudeeTextStyle provides defaultTextStyle,
            LocalTudeeColors provides colors,
            LocalSizes provides Sizes(),
        ) {
            content()
        }

    }

    private val LocalTudeeColors = staticCompositionLocalOf { TudeeColors.light }
    private val LocalTudeeTextStyle = staticCompositionLocalOf { defaultTextStyle }
    private val LocalSizes = compositionLocalOf { Sizes() }

}



