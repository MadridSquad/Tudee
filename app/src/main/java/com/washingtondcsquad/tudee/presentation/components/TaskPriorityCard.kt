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
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun TaskPriorityCard(
    priority: Priority, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = getBackgroundColor(priority),
                shape = RoundedCornerShape(100)
            )
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            painter = getIcon(priority),
            contentDescription = priority.name,
            tint = AppTheme.colors.onPrimary,
        )
        Text(
            text = priority.name.lowercase().replaceFirstChar { it.uppercase() },
            color = AppTheme.colors.onPrimary,
            style = AppTheme.textStyle.label.small
        )

    }
}

@Composable
private fun getBackgroundColor(priority: Priority): Color {
    return when (priority) {
        Priority.LOW -> AppTheme.colors.greenAccent
        Priority.MEDIUM -> AppTheme.colors.yellowAccent
        Priority.HIGH -> AppTheme.colors.pinkAccent
    }
}

@Composable
private fun getIcon(priority: Priority): Painter {
    return when (priority) {
        Priority.LOW -> painterResource(R.drawable.low_icon)
        Priority.MEDIUM -> painterResource(R.drawable.medium_icon)
        Priority.HIGH -> painterResource(R.drawable.flag_icon)
    }
}

@Preview
@Composable
private fun PreviewPriorityCard() {
    TaskPriorityCard(Priority.HIGH)
}