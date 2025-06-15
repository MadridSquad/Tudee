package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.Priority

@Composable
fun TaskPriorityCard(
    priority: Priority, modifier: Modifier = Modifier
) {
    var backgroundColor: Color
    var icon: Painter
    var title: String
    when (priority) {
        Priority.LOW -> {
            backgroundColor = AppTheme.colors.greenAccent
            icon = painterResource(R.drawable.low_priority_icon)
            title = Priority.LOW.name.lowercase().replaceFirstChar { it.uppercase() }
        }

        Priority.MEDIUM -> {
            backgroundColor = AppTheme.colors.yellowAccent
            icon = painterResource(R.drawable.medium_priority_icon)
            title = Priority.MEDIUM.name.lowercase().replaceFirstChar { it.uppercase() }

        }
        Priority.HIGH -> {
            backgroundColor = AppTheme.colors.pinkAccent
            icon = painterResource(R.drawable.flag_icon)
            title = Priority.HIGH.name.lowercase().replaceFirstChar { it.uppercase() }

        }
    }
    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(100))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            tint = AppTheme.colors.onPrimary,
        )
        Text(
            title, color = AppTheme.colors.onPrimary
        )

    }

}

@Preview
@Composable
private fun Preview() {
    TaskPriorityCard(
        priority = Priority.LOW,
    )
}