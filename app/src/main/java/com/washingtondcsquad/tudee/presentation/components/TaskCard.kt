package com.washingtondcsquad.tudee.presentation.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState

@Composable
fun TaskCard(
    taskUiState: TaskUiState, onTaskClicked: (taskId: TaskID) -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = AppTheme.colors.surfaceHigh, shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable { onTaskClicked(taskUiState.taskId) }
            .padding(start = 4.dp, end = 12.dp, top = 4.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            LoadImage(
                modifier = Modifier
                    .background(
                        color = AppTheme.colors.surfaceHigh,
                        shape = CircleShape
                    )
                    .padding(24.dp)
                    .size(32.dp),
                imageSource = taskUiState.categoryImage
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                taskUiState.taskDate?.let { date ->
                    Row(
                        modifier = Modifier
                            .background(
                                color = AppTheme.colors.surface, shape = RoundedCornerShape(100)
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
                            text = date,
                            style = AppTheme.textStyle.label.small,
                            color = AppTheme.colors.body
                        )
                    }
                }


                TaskPriorityCard(taskUiState.taskPriority, isSelected = true)
            }
        }
        Text(
            text = taskUiState.taskTitle,
            style = AppTheme.textStyle.label.large,
            color = AppTheme.colors.body,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(
            text = taskUiState.taskDescription,
            style = AppTheme.textStyle.label.small,
            color = AppTheme.colors.hint,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TaskCard(
        onTaskClicked = {}, taskUiState = TaskUiState(
            taskTitle = "Task Title",
            taskDescription = "Task Description",
            taskPriority = Priority.HIGH,
            taskDate = "2021-10-10",
            categoryImage = ImageSource.PredefinedDrawable(R.drawable.entertainment)
    )
    )
}