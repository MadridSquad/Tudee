package com.washingtondcsquad.tudee.presentation.components.analytics_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TudeeStatus

@Composable
fun TudeeStatusBanner(
    tudeeStatus: TudeeStatus,
    modifier: Modifier = Modifier,
    completedTask: Int,
    totalTasks: Int,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.widthIn(max = 231.dp)) {
            Row {
                Text(
                    tudeeStatus.title,
                    style = AppTheme.textStyle.title.small,
                    color = AppTheme.colors.title
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    painter = painterResource(tudeeStatus.icon),
                    contentDescription = TudeeStatus.ZERO_TASK.name,
                    tint = Color.Unspecified
                )
            }
            Spacer(Modifier.width(8.dp))
            Text(
                tudeeStatus.getDescription(completedTask, totalTasks),
                style = AppTheme.textStyle.body.small,
                color = AppTheme.colors.body
            )
        }
        Box(
            modifier = Modifier.size(width = 65.dp, height = 92.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(
                        color = AppTheme.colors.primary.copy(alpha = 0.16f), shape = CircleShape
                    )
            )

            Image(
                painter = painterResource(tudeeStatus.bannerImage),
                modifier = Modifier.size(width = 61.dp, height = 92.dp),
                contentDescription = TudeeStatus.STAY_WORKING.name

            )
        }
    }
}


@Preview
@Composable
private fun PreviewStatusBanner() {
    TudeeStatusBanner(completedTask = 0, totalTasks = 10, tudeeStatus = TudeeStatus.ZERO_PROGRESS)

}