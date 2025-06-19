package com.washingtondcsquad.tudee.presentation.utils

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SetStatusBarIconsColor(isDarkIcons: Boolean) {
    val activity = LocalActivity.current
    activity?.window?.let { window ->
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
            isDarkIcons
    }
}