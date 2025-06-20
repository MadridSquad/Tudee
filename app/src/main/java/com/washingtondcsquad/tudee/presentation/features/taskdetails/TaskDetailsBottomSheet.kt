@file:OptIn(ExperimentalMaterial3Api::class)

package com.washingtondcsquad.tudee.presentation.features.taskdetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.presentation.components.ProgressCard
import com.washingtondcsquad.tudee.presentation.components.TaskPriorityCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.utils.next
import com.washingtondcsquad.tudee.presentation.utils.statusColor
import com.washingtondcsquad.tudee.presentation.utils.toDisplayName
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskDetailsBottomSheet(
    taskId: Int,
    viewModel: BottomSheetTaskViewModel = koinViewModel<BottomSheetTaskViewModel>(),
    onClickTaskDetails: () -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(taskId) {
        viewModel.loadTask(taskId)
    }
    val taskState by viewModel.state.collectAsState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismiss()
        },
    ) {
        when (taskState) {
            is TaskState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is TaskState.Error -> {
                val errorMessage = (taskState as TaskState.Error).message
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Error loading task: $errorMessage",
                        color = AppTheme.colors.error,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Button(onClick = { viewModel.loadTask(taskId) }) {
                        Text("Retry")
                    }
                }
            }

            is TaskState.Loaded -> {
                val task = (taskState as TaskState.Loaded).task
                TaskDetailsContent(
                    task = task,
                    onClickTaskDetails = onClickTaskDetails,
                    onMoveToNextStatus = { viewModel.moveToNextStatus() })
            }
        }
    }
}


@Composable
private fun TaskDetailsContent(
    task: Task, onClickTaskDetails: () -> Unit, onMoveToNextStatus: () -> Unit
) {
    val isFinalStatus = task.status == TaskStatus.DONE

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Task details",
                style = AppTheme.textStyle.title.large,
                color = AppTheme.colors.title,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Image(
                modifier = Modifier
                    .background(
                        color = AppTheme.colors.surfaceHigh, shape = CircleShape
                    )
                    .padding(12.dp)
                    .size(32.dp),
                painter = painterResource(R.drawable.education_icon),
                contentDescription = "Category Image",
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = task.title,
                style = AppTheme.textStyle.title.medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = task.description,
                style = AppTheme.textStyle.body.small,
                color = AppTheme.colors.title
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp), color = AppTheme.colors.stroke
            )

            Row(Modifier.fillMaxWidth()) {
                ProgressCard(
                    title = task.status.toDisplayName(),
                    backgroundColor = statusColor(task.status),
                    modifier = Modifier.padding(end = 8.dp)
                )

                TaskPriorityCard(
                    priority = task.priority, modifier = Modifier.height(28.dp)
                )
            }
            if (!isFinalStatus) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .padding(end = 4.dp)
                            .height(52.dp)
                            .fillMaxWidth(0.25f)
                            .clip(RoundedCornerShape(100.dp))
                            .border(
                                1.dp, AppTheme.colors.stroke, RoundedCornerShape(100.dp)
                            )
                            .clickable { onClickTaskDetails() }) {
                        Image(
                            painter = painterResource(id = R.drawable.pencil_edit),
                            contentDescription = "edit screen",
                            modifier = Modifier.align(Alignment.Center)

                        )
                    }

                    Box(
                        Modifier
                            .height(52.dp)
                            .fillMaxWidth(1f)
                            .clip(RoundedCornerShape(100.dp))
                            .border(
                                1.dp, AppTheme.colors.stroke, RoundedCornerShape(100.dp)
                            )
                            .clickable(
                                onClick = {
                                    onMoveToNextStatus()
                                })
                    ) {
                        Text(
                            text = "Move to ${task.status.next().toDisplayName()}",
                            color = AppTheme.colors.primary,
                            style = AppTheme.textStyle.label.large,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

        }
    }
}

@Composable
@Preview
fun Preview() {
    //  TaskDetailsBottomSheet(taskId = 1, onClickTaskDetails = {}, onDismiss = {}, isSheetOpen = false)
}