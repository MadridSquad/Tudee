package com.washingtondcsquad.tudee.presentation.features.add_task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.presentation.components.CancelableActionLayout
import com.washingtondcsquad.tudee.presentation.components.DatePickerModal
import com.washingtondcsquad.tudee.presentation.components.TaskScreenComponents
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.AddTaskUiState
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewTaskScreen(
    onClickCancel: () -> Unit,
    onSuccessAddTask: (message: String) -> Unit,
    onErrorAddTask: (message: String) -> Unit,
    taskDate: LocalDate,
    modifier: Modifier = Modifier,
    taskStates: TaskStatus = TaskStatus.TODO,
    viewModel: AddTaskViewModel = koinViewModel(
        parameters = {
            parametersOf(
                taskDate,
            )
        }
    ),
) {
    val state by viewModel.state.collectAsState()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    AddNewTaskContent(
        state = state,
        bottomSheetState = bottomSheetState,
        taskData = taskDate,
        updateData = viewModel::updateDate,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onDateSelected = viewModel::onDateSelected,
        onShowDatePicker = viewModel::onShowDatePicker,
        onHideDatePicker = viewModel::onHideDatePicker,
        onPrioritySelected = viewModel::onPrioritySelected,
        onCategorySelected = viewModel::onCategorySelected,
        onSuccessAddTask = onSuccessAddTask,
        onErrorAddTask = onErrorAddTask,
        onClickAdd = viewModel::onClickAdd,
        onClickCancel = onClickCancel,
        taskStates  = taskStates,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddNewTaskContent(
    state: AddTaskUiState,
    bottomSheetState: SheetState,
    taskData: LocalDate,
    updateData: (data: LocalDate) -> Unit,
    onTitleChange: (newTitle: String) -> Unit,
    onDescriptionChange: (newDescription: String) -> Unit,
    onDateSelected: (dateAsMilliseconds: Long) -> Unit,
    onShowDatePicker: () -> Unit,
    onHideDatePicker: () -> Unit,
    onPrioritySelected: (priority: Priority) -> Unit,
    onCategorySelected: (category: Category) -> Unit,
    onSuccessAddTask: (String) -> Unit,
    onErrorAddTask: (String) -> Unit,
    onClickAdd: (
        onSuccess: (message: String) -> Unit,
        onError: (message: String) -> Unit,
        taskStats: TaskStatus
    ) -> Unit,
    onClickCancel: () -> Unit,
    taskStates: TaskStatus,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        updateData(taskData)
    }

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = { onClickCancel() },
        containerColor = AppTheme.colors.surface,
        sheetState = bottomSheetState,
    ) {
        if (state.isDatePickerDisplayed) {
            DatePickerModal(
                onDateSelected = { selectedDateMillis ->
                    if (selectedDateMillis != null) {
                        onDateSelected(selectedDateMillis)
                    }
                },
                onDismiss = onHideDatePicker
            )

        }

        Column(
            Modifier.fillMaxHeight(0.8f)
        ) {
            TaskScreenComponents(
                header = stringResource(R.string.add_new_task),
                taskTitle = state.taskTitle,
                onTitleChange = onTitleChange,
                taskDescription = state.taskDescription,
                onDescriptionChange = onDescriptionChange,
                taskDate = state.taskDate,
                onShowDatePicker = onShowDatePicker,
                priorityList = state.priorityList,
                selectedPriority = state.selectedPriority,
                onPrioritySelected = onPrioritySelected,
                categoryList = state.categoryList,
                onCategorySelected = onCategorySelected,
                selectedCategory = state.selectedCategory,
                modifier = Modifier.weight(1f)
            )

            CancelableActionLayout(
                modifier = Modifier,
                actionText = stringResource(R.string.save),
                actionTextColor = Color.White,
                actionBackgroundColor = AppTheme.colors.primaryGradient,
                onAction = {
                    onClickCancel()
                    onClickAdd(
                        onSuccessAddTask,
                        onErrorAddTask,
                        taskStates
                    )
                },
                onCancel = {
                    onClickCancel()
                },
                isEnabled = state.isButtonActionEnable
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true
)
@Composable
fun AddTaskPreview(

) {
    AddNewTaskContent(
        state = AddTaskUiState(
            taskDate = LocalDate.parse("2023-07-12"),
        ),
        bottomSheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        ),
        taskData = LocalDate.parse("2023-07-12"),
        updateData = {},
        onTitleChange = {},
        onDescriptionChange = {},
        onDateSelected = {},
        onShowDatePicker = {},
        onHideDatePicker = {},
        onPrioritySelected = {},
        onCategorySelected = {},
        onSuccessAddTask = {},
        onErrorAddTask = {},
        onClickAdd = { _, _, _-> },
        taskStates = TaskStatus.TODO,
        onClickCancel = {}
    )
}