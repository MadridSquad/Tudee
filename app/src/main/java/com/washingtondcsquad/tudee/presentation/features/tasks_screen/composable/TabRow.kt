package com.washingtondcsquad.tudee.presentation.features.tasks_screen.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.TabRowDefaults.PrimaryIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.TasksUiState
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun TasksTabRow(
    selectedTabIndex: Int, pagerState: PagerState, tasksUiState: TasksUiState
) {
    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        containerColor = AppTheme.colors.surfaceHigh,
        indicator = {
            PrimaryIndicator(
                Modifier.tabIndicatorOffset(
                    selectedTabIndex = selectedTabIndex,
                    matchContentSize = true
                ),
                color = AppTheme.colors.secondary,
                height = 4.dp,
                width = 70.dp //TODO this not correct to be fixed but i can't reach to another solution
            )
        }) {
        val dataInLocalDate =
            Instant.ofEpochMilli(tasksUiState.selectedDateInMillis).atZone(ZoneId.systemDefault())
                .toLocalDate()
        val realDate = dataInLocalDate.format(DateTimeFormatter.ofPattern("d-M-yyyy"))
        TasksTab(
            selectedTabIndex = selectedTabIndex,
            index = 0,
            tabName = stringResource(R.string.tasks_screen_tap_row_in_progress),
            pagerState = pagerState,
            tasksNumber = tasksUiState.tasksList.filter {
                it.taskStatus == "IN_PROGRESS" && it.taskDate == realDate
            }.size
        )
        TasksTab(
            selectedTabIndex = selectedTabIndex,
            index = 1,
            tabName = stringResource(R.string.tasks_screen_tap_row_to_do),
            pagerState = pagerState,
            tasksNumber = tasksUiState.tasksList.filter {
                it.taskStatus == "TODO" && it.taskDate == realDate
            }.size
        )
        TasksTab(
            selectedTabIndex = selectedTabIndex,
            index = 2,
            tabName = stringResource(R.string.tasks_screen_tap_row_done),
            pagerState = pagerState,
            tasksNumber = tasksUiState.tasksList.filter { it.taskStatus == "DONE" && it.taskDate == realDate }.size
        )
    }
}