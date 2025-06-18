@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.washingtondcsquad.tudee.presentation.features.taskdetails

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.presentation.components.ProgressCard
import com.washingtondcsquad.tudee.presentation.components.TaskPriorityCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.utils.next
import com.washingtondcsquad.tudee.presentation.utils.statusColor
import com.washingtondcsquad.tudee.presentation.utils.toDisplayName
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun TaskDetailsBottomSheet(
    taskId: UUID = UUID.randomUUID(),
    viewModel: BottomSheetTaskViewModel = koinViewModel<BottomSheetTaskViewModel>(),
    onClickTaskDetails: () -> Unit,
    onDismiss: () -> Unit
) {
    LaunchedEffect(taskId) {
        viewModel.loadTask(taskId)
    }
    val taskState by viewModel.state.collectAsState()
    val taskDetails = when (taskState) {
        is TaskState.Loaded -> (taskState as TaskState.Loaded).task
        else -> null
    }

    taskDetails?.let { task ->
        val isFinalStatus = task.status == TaskStatus.DONE
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        var isSheetOpen by rememberSaveable { mutableStateOf(true) }

        if (isSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    isSheetOpen = false
                    onDismiss()
                },
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
                        color = AppTheme.colors.title, modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Image(
                        modifier = Modifier
                            .background(
                                color = AppTheme.colors.surfaceHigh,
                                shape = CircleShape
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
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = AppTheme.colors.stroke
                    )

                    Row(Modifier.fillMaxWidth()) {
                        ProgressCard(
                            title = task.status.toDisplayName(),
                            backgroundColor = statusColor(task.status),
                            modifier = Modifier.padding(end = 8.dp)
                        )

                        TaskPriorityCard(
                            priority = task.priority,
                            modifier = Modifier.height(28.dp)
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
                                    .height(52.dp)
                                    .fillMaxWidth(0.25f)
                                    .clip(RoundedCornerShape(100.dp))
                                    .border(
                                        1.dp,
                                        AppTheme.colors.stroke,
                                        RoundedCornerShape(100.dp)
                                    )
                                    .clickable { onClickTaskDetails() }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.pencil_edit),
                                    contentDescription = "edit screen",
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(end = 4.dp)
                                )
                            }

                            Box(
                                Modifier
                                    .height(52.dp)
                                    .fillMaxWidth(1f)
                                    .clip(RoundedCornerShape(100.dp))
                                    .border(
                                        1.dp,
                                        AppTheme.colors.stroke,
                                        RoundedCornerShape(100.dp)
                                    )
                                    .clickable(
                                        onClick = { viewModel.moveToNextStatus() }
                                    )
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
    } ?: run {
        when (taskState) {
            is TaskState.Error -> {
                Text("Error loading task: ${(taskState as TaskState.Error).message}")
                Button(onClick = { viewModel.loadTask(taskId) }) {
                    Text("Retry")
                }
            }

            else -> CircularProgressIndicator()
        }
    }
}