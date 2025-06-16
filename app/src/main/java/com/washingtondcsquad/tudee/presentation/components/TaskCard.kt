package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import java.time.LocalDate

@Composable
fun TaskCard(
    categoryImagePainter: Painter,
    taskUiState: TaskUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = AppTheme.colors.surfaceHigh,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp)
            .padding(top = 4.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = categoryImagePainter,
                contentDescription = null,
                modifier = Modifier
                    .padding(12.dp)
                    .size(32.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = AppTheme.colors.surface,
                            shape = RoundedCornerShape(100)
                        )
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.calendar_icon),
                        contentDescription = null,
                        tint = AppTheme.colors.body
                    )
                    Text(
                        text = taskUiState.taskDate.toString(),
                        style = AppTheme.textStyle.label.small,
                        color = AppTheme.colors.body
                    )
                }

                // to be changed when project structure is merged
                TaskPriorityCard(
                    icon = painterResource(R.drawable.flag_icon),
                    title = "High",
                    backgroundColor = AppTheme.colors.pinkAccent,
                )
            }
        }
        Text(
            text = taskUiState.taskTitle,
            style = AppTheme.textStyle.label.large,
            color = AppTheme.colors.body,
        )
        Text(
            text = taskUiState.taskDescription,
            style = AppTheme.textStyle.label.small,
            color = AppTheme.colors.hint,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TaskCard(
        categoryImagePainter = painterResource(R.drawable.education_icon),
        taskUiState = TaskUiState(
            taskTitle = "Task Title",
            taskDescription = "Task Description",
            taskPriority = Priority.HIGH,
            taskDate = LocalDate.now()
        )
    )
}