package com.washingtondcsquad.tudee

import java.util.Locale

data class MainActivityUiState(
    val isDarkTheme: Boolean = false,
    val hasOnBoardingShown: Boolean = false,
    val currentAppLocale: Locale = Locale.ENGLISH
)
