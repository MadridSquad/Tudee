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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.presentation.components.DatePickerModal
import com.washingtondcsquad.tudee.presentation.components.DayCard
import com.washingtondcsquad.tudee.presentation.components.SnackBarCard
import com.washingtondcsquad.tudee.presentation.deletetask.ConfirmDeleteTask
import com.washingtondcsquad.tudee.presentation.deletetask.DeleteTaskScroll
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle
import com.washingtondcsquad.tudee.presentation.features.home.NoTasksPlaceHolder
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.composable.ChangeMonthButton
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.composable.TasksTabRow
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


@Composable
fun TasksScreen(tasksViewModel: TasksViewModel = koinViewModel()) {

    val tasksUiState by tasksViewModel.state.collectAsState()
    TasksScreenContent(
        tasksViewModel::onDaySelectedFromLazyRow,
        tasksViewModel::onDateSelectedFromPicker,
        tasksViewModel::setShowDialog,
        tasksViewModel::goToNextMonth,
        tasksViewModel::goToPreviousMonth,
        tasksViewModel::clearDatePicker,
        tasksViewModel::formatedSelectedDate,
        tasksViewModel::onTaskClicked,
        tasksUiState,
        tasksViewModel = tasksViewModel
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
    onTaskClicked: (Int) -> Unit,
    tasksUiState: TasksUiState,
    tasksViewModel: TasksViewModel
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }

    val selectedTaskToDelete = remember { mutableStateOf<TaskUiState?>(null) }

    val showSnackBar = remember { mutableStateOf(false) }


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
                modifier = Modifier.clickable { setShowDialog(true) }
            ) {
                Text(
                    tasksUiState.yearAndMonthTitle,
                    style = AppTheme.textStyle.label.medium,
                    color = AppTheme.colors.body,
                    modifier = Modifier.padding(end = 4.dp)
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
                        .clickable { onDaySelectedFromLazyRow(item.dayNumber) }
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

            val currentTasks : List<TaskUiState> = tasksUiState.tasksList
                .filter {
                    it.taskStatus == when (selectedTabIndex) {
                        0 -> {
                            TaskStatus.IN_PROGRESS
                        }

                        1 -> {
                            TaskStatus.TODO
                        }

                        else -> {
                            TaskStatus.DONE
                        }
                    }.name

                }

            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val tasksToShow = currentTasks.filter {
                    it.taskDate == formatedSelectedDate(
                        tasksUiState.selectedDateInMillis
                    ).trim()
                }

                if (tasksToShow.isNotEmpty()) {
                    itemsIndexed(tasksToShow) { index, item ->
                        DeleteTaskScroll(task = item) {
                            selectedTaskToDelete.value = item
                        }
                    }
                } else {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .padding(end = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            NoTasksPlaceHolder()
                        }
                    }
                }
            }
        }
        if (tasksUiState.showDateDialog) {
            DatePickerModal(
                onDateSelected ={ millis ->
                    millis?.let { onDateSelectedFromDatePicker(it) }
                    setShowDialog(false)
                },
                onDismiss = { setShowDialog(false) },
            )
        }

        selectedTaskToDelete.value?.let { taskToDelete ->
            ConfirmDeleteTask(
                deleteOnClick = {
                    tasksViewModel.deleteTask(taskToDelete.taskId)
                    selectedTaskToDelete.value = null
                    showSnackBar.value = true
                },
                cancelOnClick = {
                    selectedTaskToDelete.value = null
                },
                onDismiss = {
                    selectedTaskToDelete.value = null
                }
            )
        }

    }

    if (showSnackBar.value) {
        LaunchedEffect(Unit) {
            delay(3000)
            showSnackBar.value = false
        }

        SnackBarCard(
            message = stringResource(R.string.deleted_task_successfully),
            icon = painterResource(R.drawable.checkmark),
            iconTint = AppTheme.colors.greenAccent,
            iconBackgroundColor = AppTheme.colors.greenVariant,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
    }



}


