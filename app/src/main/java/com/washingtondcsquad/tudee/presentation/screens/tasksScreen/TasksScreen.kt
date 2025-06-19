package com.washingtondcsquad.tudee.presentation.screens.tasksScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.DayCard
import com.washingtondcsquad.tudee.presentation.components.TaskCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.composable.ChangeMonthButton
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.composable.DatePickerModal
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.composable.NoTasks
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.composable.TasksTabRow


@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {

    val tasksUiState by tasksViewModel.state.collectAsState()
    TasksScreenContent(
        tasksViewModel::onDaySelectedFromLazyRow,
        tasksViewModel::onDateSelectedFromPicker,
        tasksViewModel::setShowDialog,
        tasksViewModel::goToNextMonth,
        tasksViewModel::goToPreviousMonth,
        tasksViewModel::clearDatePicker,
        tasksViewModel::formatedSelectedDate,
        tasksUiState
    )
}

@Composable
fun TasksScreenContent(
    onDaySelectedFromLazyRow: (Int) -> Unit,
    onDateSelectedFromDatePicker: (Long) -> Unit,
    setShowDialog: (Boolean) -> Unit,
    gotToNextMonth: () -> Unit,
    gotToPreviousMonth: () -> Unit,
    clearDatePicker: () -> Long,
    formatedSelectedDate: (Long) -> String,
    tasksUiState: TasksUiState,
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }
    val filteredLists = listOf(
        tasksUiState.tasksList.filter { it.taskStatus == "IN_PROGRESS" },
        tasksUiState.tasksList.filter { it.taskStatus == "TODO" },
        tasksUiState.tasksList.filter { it.taskStatus == "DONE" }
    )
    Column(
        modifier = Modifier
            .background(AppTheme.colors.surfaceHigh)
            .padding(top = 70.dp)
            .fillMaxSize()
    ) {
        Text(
            "Tasks",
            style = defaultTextStyle.title.large,
            modifier = Modifier.padding(bottom = 20.dp, start = 16.dp, end = 16.dp),
            color = AppTheme.colors.title
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChangeMonthButton(R.drawable.left_arrow) { gotToPreviousMonth() }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    setShowDialog(true)
                }
            ) {
                Text(
                    tasksUiState.yearAndMonthTitle,
                    style = AppTheme.textStyle.label.medium,
                    color = AppTheme.colors.body,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.down_arrow),
                    contentDescription = "", tint = AppTheme.colors.body
                )
            }
            ChangeMonthButton(R.drawable.right_arrow) { gotToNextMonth() }
        }

        LazyRow(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(tasksUiState.monthDaysList) { index, item ->
                DayCard(
                    day = item.dayName,
                    dayNumber = item.dayNumber,
                    isSelected = item.isSelected,
                    modifier = Modifier
                        .width(65.dp)
                        .clickable {
                            onDaySelectedFromLazyRow(item.dayNumber)
                        }
                )
            }
        }

        TasksTabRow(selectedTabIndex, pagerState, tasksUiState)

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(AppTheme.colors.surface)
        ) {
            val currentTasks = filteredLists[it]

            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val tasksToShow = if (tasksUiState.isFilterEnabled) {
                    currentTasks.filter { it.taskDate == formatedSelectedDate(tasksUiState.selectedDateInMillis) }
                } else {
                    currentTasks
                }
                if (tasksToShow.isNotEmpty()) {
                    itemsIndexed(currentTasks) { index, item ->
                        TaskCard(
                            categoryImagePainter = painterResource(item.categoryImage.toInt()),
                            taskUiState = item,
                        )
                    }
                } else {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .padding(end = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            NoTasks()
                        }
                    }
                }

            }

        }

        if (tasksUiState.showDateDialog) {
            DatePickerModal(
                tasksUiState.selectedDateInMillis,
                onDateSelected = { millis ->
                    millis?.let { onDateSelectedFromDatePicker(it) }
                    setShowDialog(false)
                },
                onDismiss = { setShowDialog(false) },
                onClear = clearDatePicker

            )
        }
    }
}


