@file:OptIn(ExperimentalMaterial3Api::class)

package com.washingtondcsquad.tudee.presentation.features.taskdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.presentation.components.ProgressCard
import com.washingtondcsquad.tudee.presentation.components.TaskPriorityCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.Spacer
import com.washingtondcsquad.tudee.presentation.utils.modifierExensions.statusColor
import java.time.LocalDate
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsBottomSheet(
    taskDetails: Task,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isSheetOpen by rememberSaveable { mutableStateOf(true) }

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { isSheetOpen = false },
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
                    color = AppTheme.colors.title
                )

                Spacer.Medium()

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
                Spacer.Small()


                Text(
                    text = taskDetails.title,
                    style = AppTheme.textStyle.title.medium
                )
                Spacer.Small()
                Text(
                    text = taskDetails.description,
                    style = AppTheme.textStyle.body.small,
                    color = AppTheme.colors.title
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = AppTheme.colors.stroke
                )

                Row(Modifier.fillMaxWidth()) {

                    ProgressCard(
                        title = taskDetails.status.name,
                        backgroundColor = statusColor(taskDetails.status)
                    )

                    Spacer.Small(vertical = false)

                    TaskPriorityCard(
                        priority = taskDetails.priority, modifier = Modifier.height(28.dp)
                    )
                }

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
                            .border(1.dp, AppTheme.colors.stroke, RoundedCornerShape(100.dp))
                            .clickable { }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pencil_edit),
                            contentDescription = "edit screen",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Spacer.ExtraSmall(vertical = false)
                    Box(
                        Modifier
                            .height(52.dp)
                            .fillMaxWidth(1f)
                            .clip(RoundedCornerShape(100.dp))
                            .border(1.dp, AppTheme.colors.stroke, RoundedCornerShape(100.dp))
                            .clickable {}
                    ) {
                        Text(
                            "Move to done",
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
@Preview(showBackground = true)
fun TaskDetailsBottomSheetPreview() {
    TaskDetailsBottomSheet(
        taskDetails = Task(
            id = UUID.randomUUID(), categoryId = UUID.randomUUID(),
            title = "Organize Study Desk",
            description = "Solve all exercises from page 45 to 50 in the textbook, Solve all exercises from page 45 to 50 in the textbook.",
            date = LocalDate.now(),
            status = TaskStatus.IN_PROGRESS,
            priority = Priority.HIGH,
        )
    )
}