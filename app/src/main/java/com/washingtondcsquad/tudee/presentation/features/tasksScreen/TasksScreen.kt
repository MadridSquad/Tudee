package com.washingtondcsquad.tudee.presentation.features.tasksScreen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.LocalInnerPaddingProvider
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.SnackbarHandler
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.presentation.components.DayCard
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarController
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarEvent
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle
import com.washingtondcsquad.tudee.presentation.features.add_task.AddNewTaskScreen
import com.washingtondcsquad.tudee.presentation.features.delete_task.ConfirmDeleteTask
import com.washingtondcsquad.tudee.presentation.features.delete_task.DeleteTaskScroll
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.features.tasksScreen.composable.ChangeMonthButton
import com.washingtondcsquad.tudee.presentation.features.tasksScreen.composable.DatePickerComponent
import com.washingtondcsquad.tudee.presentation.features.tasksScreen.composable.NoTasks
import com.washingtondcsquad.tudee.presentation.features.tasksScreen.composable.ShowEditTaskScreen
import com.washingtondcsquad.tudee.presentation.features.tasksScreen.composable.TasksTabRow
import com.washingtondcsquad.tudee.presentation.utils.modifierExensions.noRippleClick
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.androidx.compose.koinViewModel


@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel = koinViewModel(),
) {

    val tasksUiState by tasksViewModel.state.collectAsState()
    TasksScreenContent(
        tasksViewModel::onDaySelectedFromLazyRow,
        tasksViewModel::onDateSelectedFromPicker,
        tasksViewModel::setShowDialog,
        tasksViewModel::goToNextMonth,
        tasksViewModel::goToPreviousMonth,
        tasksViewModel::formatedSelectedDate,
        tasksViewModel::clearDatePicker,
        tasksUiState,
        tasksViewModel = tasksViewModel,
        modifier = modifier
    )
}

@Composable
fun TasksScreenContent(
    onDaySelectedFromLazyRow: (Int) -> Unit,
    onDateSelectedFromDatePicker: (Long) -> Unit,
    setShowDialog: (Boolean) -> Unit,
    gotToNextMonth: () -> Unit,
    gotToPreviousMonth: () -> Unit,
    formatedSelectedDate: (Long) -> String,
    onClearDatePicker: () -> Long,
    tasksUiState: TasksUiState,
    tasksViewModel: TasksViewModel,
    modifier: Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }
    val selectedTaskToDelete = remember { mutableStateOf<TaskUiState?>(null) }
    val showSnackBar = remember { mutableStateOf(false) }
    val showTaskDetails = remember { mutableStateOf(false) }
    val editTaskResult = remember { mutableStateOf(false to "") }
    val selectedTaskIdToEdit = remember { mutableStateOf<TaskID?>(null) }
    val lazyRowState = rememberLazyListState()
    val density = LocalDensity.current
    var showAddNewTaskBottomSheet by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()


    LaunchedEffect(editTaskResult.value) {
        if (editTaskResult.value.second.isNotEmpty()) {
            SnackbarController.sendEvent(SnackbarEvent(message = editTaskResult.value.second))
            editTaskResult.value = false to ""
        }
    }
    LaunchedEffect(tasksUiState.monthDaysList) {
        val selectedIndex = tasksUiState.monthDaysList.indexOfFirst { it.isSelected }
        if (selectedIndex != -1) {
            val itemWidthPx = with(density) { 65.dp.toPx() }
            val centerOffset =
                (lazyRowState.layoutInfo.viewportEndOffset / 2) - (itemWidthPx / 2).toInt()
            lazyRowState.animateScrollToItem(
                index = selectedIndex, scrollOffset = -centerOffset
            )
        }
    }

    Box(
        modifier = Modifier.padding(LocalInnerPaddingProvider.current)
    ) {

        Column(
            modifier = Modifier.background(AppTheme.colors.surfaceHigh)


        ) {

            Text(
                stringResource(R.string.tasks_screen_title),
                style = defaultTextStyle.title.large,
                modifier = Modifier.padding(
                    bottom = 20.dp, top = 10.dp, start = 16.dp, end = 16.dp
                ),
                color = AppTheme.colors.title
            )
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
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
                        modifier = Modifier.clickable { setShowDialog(true) }) {
                        Text(
                            tasksUiState.yearAndMonthTitle,
                            style = AppTheme.textStyle.label.medium,
                            color = AppTheme.colors.body,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.down_arrow),
                            contentDescription = "",
                            tint = AppTheme.colors.body
                        )
                    }
                    ChangeMonthButton(R.drawable.right_arrow) { gotToNextMonth() }
                }
            }
            LazyRow(
                state = lazyRowState,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),

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
                            })
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

                val currentTasks: List<TaskUiState> = tasksUiState.tasksList.filter {
                    it.taskStatus == when (selectedTabIndex) {
                        0 -> TaskStatus.IN_PROGRESS
                        1 -> TaskStatus.TODO
                        else -> TaskStatus.DONE
                    }.name
                }

                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 12.dp, start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val tasksToShow = if (tasksUiState.isFilterEnabled) {
                        currentTasks.filter {
                            it.taskDate == formatedSelectedDate(
                                tasksUiState.selectedDateInMillis
                            ).trim()
                        }
                    } else {
                        currentTasks
                    }

                    if (tasksToShow.isNotEmpty()) {
                        itemsIndexed(tasksToShow) { index, item ->
                            DeleteTaskScroll(
                                task = item,
                                onClick = {
                                    selectedTaskIdToEdit.value = item.taskId
                                    showTaskDetails.value = true
                                },
                            ) {
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
                                NoTasks()
                            }
                        }
                    }
                }
            }
            if (tasksUiState.showDateDialog) {
                DatePickerComponent(tasksUiState.selectedDateInMillis, onDateSelected = { millis ->
                    millis?.let { onDateSelectedFromDatePicker(it) }
                    setShowDialog(false)
                }, onDismiss = { setShowDialog(false) }, onClear = { onClearDatePicker() }

                )
            }

            selectedTaskToDelete.value?.let { taskToDelete ->
                ConfirmDeleteTask(deleteOnClick = {
                    tasksViewModel.deleteTask(taskToDelete)
                    selectedTaskToDelete.value = null
                    showSnackBar.value = true
                }, cancelOnClick = {
                    selectedTaskToDelete.value = null
                }, onDismiss = {
                    selectedTaskToDelete.value = null
                })
            }

        }
        SnackbarHandler()

        if (showAddNewTaskBottomSheet) {
            AddNewTaskScreen(
                onClickCancel = {
                    showAddNewTaskBottomSheet = false
                },
                onSuccessAddTask = { successMessage ->


                },
                onErrorAddTask = { errorMessage ->

                },

                taskDate = Instant.fromEpochMilliseconds(
                    tasksUiState.selectedDateInMillis
                ).toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ).date

                , taskStates = when(pagerState.currentPage){
                    0 -> {
                        TaskStatus.IN_PROGRESS
                    }
                    1->{
                        TaskStatus.TODO
                    }
                    else -> {
                        TaskStatus.DONE
                    }
                }
            )
        }


        FabIcon(
            modifier = Modifier
                .noRippleClick {
                    showAddNewTaskBottomSheet = true
                }
                .align(Alignment.BottomEnd))
    }


    val snackbarMessage = stringResource(R.string.deleted_task_successfully)
    LaunchedEffect(showSnackBar.value) {
        if (showSnackBar.value) {
            SnackbarController.sendEvent(SnackbarEvent(message = snackbarMessage))
            showSnackBar.value = false
        }
    }

    if (showTaskDetails.value && selectedTaskIdToEdit.value != null) {
        ShowEditTaskScreen(
            showTaskDetails = showTaskDetails,
            editTaskResult = editTaskResult,
            taskId = selectedTaskIdToEdit.value!!
        )
    }

    LaunchedEffect(editTaskResult.value) {
        if (editTaskResult.value.second.isNotEmpty()) {
            SnackbarController.sendEvent(SnackbarEvent(message = editTaskResult.value.second))
            editTaskResult.value = false to ""
        }
    }
}

@Composable
private fun FabIcon(modifier: Modifier) {
    Icon(
        painter = painterResource(R.drawable.note_add_icon),
        contentDescription = null,
        tint = AppTheme.colors.onPrimary,
        modifier = modifier
            .padding(end = 16.dp, bottom = 16.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                clip = false,
            )
            .background(
                brush = Brush.linearGradient(AppTheme.colors.primaryGradient), shape = CircleShape
            )
            .padding(18.dp)
            .size(28.dp)
    )
}

