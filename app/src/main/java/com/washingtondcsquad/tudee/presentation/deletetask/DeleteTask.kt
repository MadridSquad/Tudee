package com.washingtondcsquad.tudee.presentation.deletetask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.TaskCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState

@Composable
fun DeleteTaskBackground(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(111.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(AppTheme.colors.errorVariant),
        contentAlignment = Alignment.CenterEnd
    ) {

        IconButton(
            onClick = {},
        ) {
            Box(
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.delete_icon),
                    contentDescription = "delete icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 1.67.dp, end = 2.67.dp),
                    tint = AppTheme.colors.error
                )
            }
        }

    }


}

@Preview(showSystemUi = true)
@Composable
fun DeleteTaskScroll() {

    Row(
        modifier = Modifier.padding(top = 50.dp, end = 16.dp, start = 16.dp)
    ) {
        val dismissState = rememberSwipeToDismissBoxState(
            confirmValueChange = {
                false
            }
        )
        SwipeToDismissBox(
            state = dismissState,
            backgroundContent = {
                DeleteTaskBackground(
                    modifier = Modifier
                )
            },
            content = {
                TaskCard(
                    modifier = Modifier.height(111.dp),
                    categoryImagePainter = painterResource(R.drawable.education_icon),
                    taskUiState = TaskUiState(
                        taskTitle = "Task Title",
                        taskDescription = "Task Description",
                        taskPriority = "High",
                        taskDate = "2021-10-10",
                    )
                )
            },
            enableDismissFromStartToEnd = false,
            enableDismissFromEndToStart = true,
        )
    }

}


