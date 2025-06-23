package com.washingtondcsquad.tudee

import java.util.Locale

data class MainState(
    val isDarkTheme: Boolean = false,
    val hasOnBoardingShown: Boolean? = null,
    val currentAppLocale: Locale = Locale.ENGLISH
)
