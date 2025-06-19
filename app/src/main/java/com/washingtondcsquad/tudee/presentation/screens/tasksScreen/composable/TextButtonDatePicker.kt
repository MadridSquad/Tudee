package com.washingtondcsquad.tudee.presentation.screens.tasksScreen.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun TextButtonDatePicker(title: String) {
    Text(
        title,
        style = AppTheme.textStyle.label.large,
        color = AppTheme.colors.primary
    )
}