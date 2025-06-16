package com.washingtondcsquad.tudee.presentation.components.analytics_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme
import com.washingtondcsquad.tudee.presentation.design.theme.TudeeTheme

@Composable
fun OverViewSection(
    doneCount: Int, inProgressCount: Int, toDoCount: Int, modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text("Overview", style = AppTheme.textStyle.title.large, color = AppTheme.colors.title)
        Spacer(Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TaskStatusCountCard(TaskStatus.DONE, doneCount, Modifier.weight(1f))
            TaskStatusCountCard(TaskStatus.IN_PROGRESS, inProgressCount, Modifier.weight(1f))
            TaskStatusCountCard(TaskStatus.TODO, toDoCount, Modifier.weight(1f))
        }
    }
}

@Composable
fun TaskStatusCountCard(taskStatus: TaskStatus, count: Int, modifier: Modifier = Modifier) {
    var bgColor: Color
    var icon: Painter
    var title: String
    when (taskStatus) {
        TaskStatus.TODO -> {
            title = "To-Do"
            bgColor = AppTheme.colors.purpleAccent
            icon = painterResource(R.drawable.todo_status_icon)
        }

        TaskStatus.IN_PROGRESS -> {
            title = "In progress"
            bgColor = AppTheme.colors.yellowAccent
            icon = painterResource(R.drawable.inprogress_status_icon)

        }

        TaskStatus.DONE -> {
            title = "Done"
            bgColor = AppTheme.colors.greenAccent
            icon = painterResource(R.drawable.done_status_icon)

        }
    }

    Box(
        modifier
            .height(112.dp)
            .background(bgColor, shape = RoundedCornerShape(20.dp))
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0x3DFFFFFF), shape = RoundedCornerShape(12.dp)
                    )
                    .border(1.dp, color = Color(0x1FFFFFFF), shape = RoundedCornerShape(12.dp))
            ) {
                Icon(
                    painter = icon,
                    contentDescription = taskStatus.name,
                    modifier = Modifier.padding(8.dp),
                    tint = AppTheme.colors.onPrimary
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                count.toString(),
                style = AppTheme.textStyle.headLine.medium,
                color = AppTheme.colors.onPrimary
            )
            Text(
                title,
                style = AppTheme.textStyle.label.small,
                color = AppTheme.colors.onPrimaryCaption
            )

        }

        Box(
            Modifier
                .size(45.dp)
                //.background(AppTheme.colors.onPrimary, shape = CircleShape)
                .align(
                    Alignment.TopEnd
                )
            //.offset(x = 25.dp, y = -25.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.status_card_background),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }


}

@Preview
@Composable
private fun PreviewTaskStatusCount() {
    TudeeTheme(useDarkTheme = false) {
        //TaskStatusCountCard(TaskStatus.DONE, 6)
        OverViewSection(doneCount = 6, inProgressCount = 6, toDoCount = 6)


    }

}