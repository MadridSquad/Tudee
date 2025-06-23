package com.washingtondcsquad.tudee.presentation.utils

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SetStatusBarIconsColor(isDarkIcons: Boolean) {
    val activity = LocalActivity.current
    val window = activity?.window

    SideEffect {
        window?.let {
            WindowInsetsControllerCompat(it, it.decorView).isAppearanceLightStatusBars = isDarkIcons
        }
    }
}