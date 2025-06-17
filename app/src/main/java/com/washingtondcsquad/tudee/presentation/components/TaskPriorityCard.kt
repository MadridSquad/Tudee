package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.presentation.design.AppTheme


@Composable
fun TaskPriorityCard(
    priority: Priority,
    isSelected: Boolean,
    onClick: (priority: Priority) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = Color(0x00000000)
        ),
        shape = RoundedCornerShape(100),
        modifier = modifier

    ){
    Row(
        modifier = modifier
        .clickable(onClick = { onClick(priority) })
        .background(
                color = getBackgroundColor(priority, isSelected),
                shape = RoundedCornerShape(100)
            )
            .padding(vertical = 6.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            painter = getIcon(priority),
            contentDescription = priority.name,
            tint = AppTheme.colors.hint,
        )
        Text(
            text = priority.name.lowercase().replaceFirstChar { it.uppercase() },
            color = AppTheme.colors.hint
        )
    }
    }
}

@Composable
private fun getBackgroundColor(
    priority: Priority,
    isSelected: Boolean = false
    ): Color {
    return if(isSelected) {
        when (priority) {
            Priority.LOW -> AppTheme.colors.greenAccent
            Priority.MEDIUM -> AppTheme.colors.yellowAccent
            Priority.HIGH -> AppTheme.colors.pinkAccent
        }
    }
    else { AppTheme.colors.surfaceLow }
}

@Composable
private fun getIcon(priority: Priority): Painter {
    return when (priority) {
        // need to change icons later
        Priority.LOW -> painterResource(R.drawable.flag_icon)
        Priority.MEDIUM -> painterResource(R.drawable.flag_icon)
        Priority.HIGH -> painterResource(R.drawable.flag_icon)
    }
}


//@Preview
//@Composable
//private fun PreviewPriorityCard() {
//    TaskPriorityCard(
//        priority = Priority.LOW,
//        onClick = {},
//        isSelected = true
//    )
//}