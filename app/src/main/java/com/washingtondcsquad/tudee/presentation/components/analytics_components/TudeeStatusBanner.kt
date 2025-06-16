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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TudeeStatus

@Composable
fun TudeeStatusBanner(
    tudeeStatus: TudeeStatus,
    completedTask: Int? = null,
    totalTasks: Int? = null,
    modifier: Modifier = Modifier
) {
    var title: String
    var icon: Painter
    var description: String
    var bannerImage: Painter
    when (tudeeStatus) {
        TudeeStatus.STAY_WORKING -> {
            title = "Stay working!"
            icon = painterResource(R.drawable.status_neutral_icon)
            description =
                "You've completed ${completedTask ?: ""} out of ${totalTasks ?: ""} tasks Keep going!"
            bannerImage = painterResource(R.drawable.stay_working_status_image)
        }

        TudeeStatus.DOING_AMAZING -> {
            title = "Tadaa!"
            icon = painterResource(R.drawable.status_happy_icon)
            description = "You’re doing amazing!!!\n" + "Tudee is proud of you."
            bannerImage = painterResource(R.drawable.doing_amazing_status_image)
        }

        TudeeStatus.ZERO_PROGRESS -> {
            title = "Zero progress?!"
            icon = painterResource(R.drawable.status_very_sad_icon)
            description = "You just scrolling, not working. Tudee is watching. back to work!!!"
            bannerImage = painterResource(R.drawable.zero_progress_status_image)
        }

        TudeeStatus.ZERO_TASK -> {
            title = "Nothing on your list…"
            icon = painterResource(R.drawable.status_sad_icon)
            description = "Fill your day with something awesome."
            bannerImage = painterResource(R.drawable.stay_working_status_image)
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.widthIn(max = 231.dp)) {
            Row {
                Text(title, style = AppTheme.textStyle.title.small, color = AppTheme.colors.title)
                Spacer(Modifier.width(8.dp))
                Icon(
                    painter = icon,
                    contentDescription = TudeeStatus.ZERO_TASK.name,
                    tint = Color.Unspecified
                )
            }
            Spacer(Modifier.width(9.dp))
            Text(
                description, style = AppTheme.textStyle.body.small, color = AppTheme.colors.body
            )
        }
        Box(
            modifier = Modifier.size(width = 65.dp, height = 92.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(color = AppTheme.colors.primaryVarient, shape = CircleShape)
            )

            Image(
                painter = bannerImage,
                modifier = Modifier.size(width = 61.dp, height = 92.dp),
                contentDescription = TudeeStatus.STAY_WORKING.name

            )
        }


    }
}


@Preview
@Composable
private fun PreviewStatusBanner() {
    TudeeStatusBanner(tudeeStatus = TudeeStatus.ZERO_PROGRESS)

}