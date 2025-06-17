package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.Spacer

@Composable
fun ProgressCard(
    title: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(28.dp)
            .wrapContentWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(100)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "•",
            color = backgroundColor.copy(alpha = 1.0f),
            style = AppTheme.textStyle.title.small,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 12.dp)

        )
        Spacer.ExtraSmall(vertical = false)
        Text(
            title.lowercase().replace("_", " "),
            color = backgroundColor.copy(alpha = 1.0f),
            style = AppTheme.textStyle.label.small,
            modifier = Modifier.padding(end = 12.dp)
        )
    }

}