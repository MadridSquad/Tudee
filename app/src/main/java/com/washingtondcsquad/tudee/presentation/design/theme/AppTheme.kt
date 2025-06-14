package com.washingtondcsquad.tudee.presentation.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import com.washingtondcsquad.tudee.presentation.design.colors.TudeeColors
import com.washingtondcsquad.tudee.presentation.design.textStyle.TudeeTextStyle

@Stable
object AppTheme {

    val textStyle: TudeeTextStyle
        @Composable
        @ReadOnlyComposable
        get()= LocalTudeeTextStyle.current

    val tudeeColors: TudeeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalTudeeColors.current
}



