package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme

@Composable
fun DayCard(
    day: String,
    dayNumber: Int,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val backgroundColor: List<Color> =
        if (isSelected) AppTheme.colors.primaryGradient else List(2) { AppTheme.colors.surface }

    Column(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(backgroundColor),
                shape = RoundedCornerShape(16.dp),
            )
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = dayNumber.toString(),
            color = if (isSelected) AppTheme.colors.onPrimary else AppTheme.colors.body,
            style = AppTheme.textStyle.title.medium,
        )

        Text(
            text = day,
            color = if (isSelected) AppTheme.colors.onPrimaryCaption else AppTheme.colors.hint,
            style = AppTheme.textStyle.body.small,
        )

    }
}


@Preview
@Composable
private fun Preview() {
    DayCard(
        day = "Mon",
        dayNumber = 1,
        modifier = Modifier,
        isSelected = true,
    )
}