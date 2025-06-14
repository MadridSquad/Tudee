package com.washingtondcsquad.tudee.presentation.design.textStyle

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.washingtondcsquad.tudee.R

val nunitot = FontFamily(
    Font(R.font.nunito, weight = FontWeight.Normal),
    Font(R.font.nunito, weight = FontWeight.Medium),
    Font(R.font.nunito, weight = FontWeight.SemiBold),
)
val LocalTudeeTextStyle = staticCompositionLocalOf { defaultTextStyle }