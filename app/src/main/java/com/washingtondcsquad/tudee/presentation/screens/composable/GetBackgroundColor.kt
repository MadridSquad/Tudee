package com.washingtondcsquad.tudee.presentation.screens.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun getBackgroundColor(
    priority: Priority
): Color {
    return when(priority){
        Priority.LOW -> AppTheme.colors.greenAccent
        Priority.MEDIUM -> AppTheme.colors.yellowAccent
        Priority.HIGH -> AppTheme.colors.pinkAccent
    }
}