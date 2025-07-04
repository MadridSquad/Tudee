package com.washingtondcsquad.tudee.presentation.features.tasksScreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun TasksScreenFabIcon(modifier: Modifier) {
    Icon(
        painter = painterResource(R.drawable.note_add_icon),
        contentDescription = null,
        tint = AppTheme.colors.onPrimary,
        modifier = modifier
            .padding(end = 16.dp, bottom = 16.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                clip = false,
            )
            .background(
                brush = Brush.linearGradient(AppTheme.colors.primaryGradient), shape = CircleShape
            )
            .padding(18.dp)
            .size(28.dp)
    )
}