package com.washingtondcsquad.tudee.presentation.features.tasks_screen.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.TabRowDefaults.PrimaryIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.TasksUiState
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksTabRow(
    selectedTabIndex: Int, pagerState: PagerState, tasksUiState: TasksUiState
    ,
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
                height = 4.dp
            )
        }
    ) {
        val dataInLocalDate = Instant.ofEpochMilli(tasksUiState.selectedDateInMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val realDate = dataInLocalDate.format(DateTimeFormatter.ofPattern("d-M-yyyy"))
        TasksTab(
            selectedTabIndex = selectedTabIndex,
            index = 0,
            tabName = "In progress",
            pagerState = pagerState,
            tasksNumber = tasksUiState.tasksList.filter { it.taskStatus == "IN_PROGRESS" &&
                    it.taskDate == realDate}.size
        )
        TasksTab(
            selectedTabIndex = selectedTabIndex,
            index = 1,
            tabName = "To Do",
            pagerState = pagerState,
            tasksNumber = tasksUiState.tasksList.filter { it.taskStatus == "TODO" &&
                    it.taskDate == realDate}.size
        )
        TasksTab(
            selectedTabIndex = selectedTabIndex,
            index = 2,
            tabName = "Done",
            pagerState = pagerState,
            tasksNumber = tasksUiState.tasksList.filter { it.taskStatus == "DONE" &&  it.taskDate == realDate}.size
        )
    }
}