package com.washingtondcsquad.tudee.presentation.features.tasksScreen.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun ChangeMonthButton(@DrawableRes icon: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .border(width = 1.dp, color = AppTheme.colors.stroke, shape = CircleShape)
            .size(32.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = stringResource(R.string.empty),
            tint = AppTheme.colors.body
        )
    }
}