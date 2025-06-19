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
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun TaskPriorityCard(
    icon: Painter,
    title: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
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
            title,
            color = AppTheme.colors.onPrimary
        )

    }

}

@Preview
@Composable
private fun Preview() {
    TaskPriorityCard(
        icon = painterResource(
            id = R.drawable.flag_icon
        ), title = "High", backgroundColor = AppTheme.colors.pinkAccent,

    )
}