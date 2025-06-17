package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.washingtondcsquad.tudee.presentation.design.AppTheme

import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import com.washingtondcsquad.tudee.domain.entity.Priority

@Composable
fun TaskPriorityCard(
    priority: Priority,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val title: String
    val iconRes: Int
    val backgroundColor: Color

    when (priority) {
        Priority.LOW -> {
            title = "Low"
            iconRes = R.drawable.flag_icon
            backgroundColor = Color(0xFF38C2B4)
        }
        Priority.MEDIUM -> {
            title = "Medium"
            iconRes = R.drawable.flag_icon
            backgroundColor = Color(0xFFFDD264)
        }
        Priority.HIGH -> {
            title = "High"
            iconRes = R.drawable.flag_icon
            backgroundColor = Color(0xFFF79494)
        }
    }

    val borderColor = if (isSelected) AppTheme.colors.primary else Color.Transparent

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .clickable(onClick = onClick)
            .border(width = 2.dp, color = borderColor, shape = RoundedCornerShape(100)) // نضيف الحدود
            .background(color = backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            tint = Color.White,
        )
        Text(
            text = title,
            color = Color.White,
            style = AppTheme.textStyle.label.large
        )
    }
}