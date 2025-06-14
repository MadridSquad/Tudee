package com.washingtondcsquad.tudee.presentation.design.theme

import androidx.compose.runtime.staticCompositionLocalOf
import com.washingtondcsquad.tudee.presentation.design.colors.LightTudeeColors
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle

val LocalTudeeTextStyle = staticCompositionLocalOf { defaultTextStyle }

val LocalTudeeColors = staticCompositionLocalOf { LightTudeeColors }