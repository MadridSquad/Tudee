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
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.domain.entity.Priority


@Composable
fun TaskPriorityCard(
    icon: Painter ,
    title: String,
    backgroundColor: Color ,
    backgroundColorNotSelected : Color = AppTheme.colors.surfaceLow ,
    isSelected: Boolean= false,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.transparentColor
        ),
        shape = RoundedCornerShape(100),
        modifier = modifier

    ) {
        Row(
            modifier = modifier
                .clickable(onClick = { onClick() })
                .background(
                    color =if (isSelected) backgroundColor else backgroundColorNotSelected,
                    shape = RoundedCornerShape(100)
                )
                .padding(vertical = 6.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Icon(
                painter =icon,
                contentDescription = title,
                tint = getColors(isSelected)
            )
            Text(
                text = title.lowercase().replaceFirstChar { it.uppercase() },
                color = getColors(isSelected)
            )

        }

    }
}


@Composable
private fun getColors(isSelected: Boolean = false): Color {
    return if (isSelected) Color.White else AppTheme.colors.hint
}
