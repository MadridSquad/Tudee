package com.washingtondcsquad.tudee.presentation.features.category_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.TabRowDefaults.PrimaryIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.composable.TasksTab

@Composable
fun TaskStatusTabs(
    selectedTabIndex: Int,
    inProgressCount: Int,
    toDoCount: Int,
    doneCount: Int,
    pagerState: PagerState,
) {
    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        containerColor = AppTheme.colors.surfaceHigh,
        indicator = {
            PrimaryIndicator(
                Modifier.tabIndicatorOffset(selectedTabIndex, matchContentSize = true),
                color = AppTheme.colors.secondary,
                height = 4.dp,
                width = 103.dp
            )
        }
    ) {

        TasksTab(
            selectedTabIndex = selectedTabIndex,
            index = 0,
            tabName = "In progress",
            pagerState = pagerState,
            tasksNumber = inProgressCount
        )
        TasksTab(
            selectedTabIndex = selectedTabIndex,
            index = 1,
            tabName = "To Do",
            pagerState = pagerState,
            tasksNumber = toDoCount
        )
        TasksTab(
            selectedTabIndex = selectedTabIndex,
            index = 2,
            tabName = "Done",
            pagerState = pagerState,
            tasksNumber = doneCount
        )
    }
}
