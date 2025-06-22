package com.washingtondcsquad.tudee.presentation.utils

import android.graphics.Color
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SetStatusBarIconsColor(backgroundColor: Color,isDarkIcons: Boolean) {
    val activity = LocalActivity.current
    val window = activity?.window

    SideEffect {
        window?.statusBarColor = backgroundColor.toArgb()

        window?.let {
            WindowInsetsControllerCompat(it, it.decorView).isAppearanceLightStatusBars = isDarkIcons
        }
    }
}