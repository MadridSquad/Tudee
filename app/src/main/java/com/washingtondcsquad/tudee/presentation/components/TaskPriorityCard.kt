package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.presentation.design.AppTheme


@Composable
fun TaskPriorityCard(
    priority: Priority,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(
                color = getBackgroundColor(priority, true),
                shape = RoundedCornerShape(100)
            )
            .padding(vertical = 6.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            painter = painterResource(priority.getIconResource()),
            contentDescription = priority.name,
            tint =if (isSelected ) AppTheme.colors.onPrimary else AppTheme.colors.hint,
        )
        Text(
            text = priority.getTitle().lowercase().replaceFirstChar { it.uppercase() },
            color = if (isSelected ) AppTheme.colors.onPrimary else AppTheme.colors.hint,
        )

    }
}

@Composable
private fun getBackgroundColor(
    priority: Priority,
    isSelected: Boolean = false
): Color {
    return if(isSelected){
        when (priority) {
            Priority.LOW -> AppTheme.colors.greenAccent
            Priority.MEDIUM -> AppTheme.colors.yellowAccent
            Priority.HIGH -> AppTheme.colors.pinkAccent
        }
    }else{
        AppTheme.colors.surfaceLow
    }
}

private fun Priority.getIconResource(): Int {
    return when (this) {
        Priority.LOW -> R.drawable.low_icon
        Priority.MEDIUM -> R.drawable.medium_icon
        Priority.HIGH -> R.drawable.priority_high
    }
}


@Preview
@Composable
private fun PreviewPriorityCard() {
    TaskPriorityCard(Priority.HIGH)
}