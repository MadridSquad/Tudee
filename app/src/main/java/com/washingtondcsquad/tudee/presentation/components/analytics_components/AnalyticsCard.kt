package com.washingtondcsquad.tudee.presentation.components.analytics_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme
import com.washingtondcsquad.tudee.presentation.design.theme.TudeeTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TudeeStatus
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AnalyticsCard(
    doneCount: Int, inProgressCount: Int, toDoCount: Int, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = AppTheme.colors.surfaceHigh)
            .padding(top = 8.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth()


    ) {

        Row {
            Icon(
                painter = painterResource(R.drawable.calendar_icon),
                contentDescription = "calender icon",
                tint = AppTheme.colors.body,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "Today, ${LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}",
                style = AppTheme.textStyle.label.medium
            )
        }
        TudeeStatusBanner(
            tudeeStatus = determineTudeeStatus(
                doneCount = doneCount, inProgressCount = inProgressCount, toDoCount = toDoCount
            ),
            completedTask = doneCount,
            totalTasks = doneCount + inProgressCount + toDoCount,
        )
        Spacer(Modifier.height(8.dp))
        OverViewSection(
            doneCount = doneCount,
            inProgressCount = inProgressCount,
            toDoCount = toDoCount,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

fun determineTudeeStatus(doneCount: Int, inProgressCount: Int, toDoCount: Int): TudeeStatus {
    val totalCount = doneCount + inProgressCount + toDoCount
    if (totalCount == 0) {
        return TudeeStatus.ZERO_TASK
    } else if (doneCount >= totalCount / 2) {
        return TudeeStatus.DOING_AMAZING
    } else if (inProgressCount == 0 && doneCount == 0) {
        return TudeeStatus.ZERO_PROGRESS
    } else if (doneCount <= toDoCount / 2) {
        return TudeeStatus.STAY_WORKING
    } else {
        return TudeeStatus.DOING_AMAZING
    }
}

@Preview(showBackground = true,
    backgroundColor = 0xFF7D7D7D
)
@Composable
private fun PreviewAnalyticsCard() {
    AnalyticsCard(
        doneCount = 1, inProgressCount = 0, toDoCount = 10, modifier = Modifier
    )
}

