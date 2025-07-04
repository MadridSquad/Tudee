package com.washingtondcsquad.tudee.presentation.features.tasks_screen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import kotlinx.coroutines.launch

@Composable
fun TasksTab(
    selectedTabIndex: Int,
    index: Int,
    tabName: String,
    pagerState: PagerState,
    tasksNumber: Int
) {
    val scope = rememberCoroutineScope()

    Tab(
        selected = selectedTabIndex == index,
        selectedContentColor = Color.Red,
        unselectedContentColor = Color.Black,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        },
        text = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = tabName,
                    color = if (selectedTabIndex == index) AppTheme.colors.title
                    else AppTheme.colors.hint,
                    style = if (selectedTabIndex == index) AppTheme.textStyle.label.medium
                    else AppTheme.textStyle.label.small
                )
                if (selectedTabIndex == index) {
                    Box(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clip(CircleShape)
                            .size(28.dp)
                            .background(AppTheme.colors.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            tasksNumber.toString(),
                            style = AppTheme.textStyle.label.medium,
                            color = AppTheme.colors.body
                        )
                    }
                }

            }
        },
    )
}