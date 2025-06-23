package com.washingtondcsquad.tudee.presentation.screens.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.CancelableActionLayout
import com.washingtondcsquad.tudee.presentation.components.DatePickerModal
import com.washingtondcsquad.tudee.presentation.components.TaskScreenComponents
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.edit_task.EditTaskViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    onCancelAddTaskBottomSheet: () -> Unit,
    onActionResult: (success: Boolean, message: String) -> Unit,
    taskId: Int,
    viewModel: EditTaskViewModel = koinViewModel(
        parameters = { parametersOf(taskId) }
    ),
) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        ModalBottomSheet(
            onDismissRequest = { onCancelAddTaskBottomSheet() },
            containerColor = AppTheme.colors.surface,
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            ),
        ) {
            if (state.isDatePickerDisplayed) {
                DatePickerModal(
                    onDateSelected = { selectedDateMillis ->
                        if (selectedDateMillis != null) {
                            viewModel.onDateSelected(selectedDateMillis)
                        }
                    },
                    onDismiss = viewModel::onHideDatePicker
                )
            }

            Column(
                Modifier.fillMaxHeight(0.8f)
            ) {

                TaskScreenComponents(
                    modifier = Modifier.weight(1f),
                    header = stringResource(id = R.string.edit_task),
                    taskTitle = state.taskTitle,
                    onTitleChange = viewModel::onTitleChange,
                    taskDescription = state.taskDescription,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    taskDate = state.taskDate,
                    onShowDatePicker = viewModel::onShowDatePicker,
                    priorityList = state.priorityList,
                    selectedPriority = state.selectedPriority,
                    onPrioritySelected = viewModel::onPrioritySelected,
                    categoryList = state.categoryList,
                    onCategorySelected = viewModel::onCategorySelected,
                    selectedCategory = state.selectedCategory
                )

                CancelableActionLayout(
                    modifier = Modifier,
                    actionText = stringResource(R.string.save),
                    actionTextColor = Color.White,
                    actionBackgroundColor = AppTheme.colors.primaryGradient,
                    onAction = {
                        viewModel.onClickEditButton (
                            onSuccess = { message ->
                                onActionResult(true, message) },
                            onError = { message ->
                                onActionResult(false, message)  }
                        )
                    },
                    onCancel = onCancelAddTaskBottomSheet,
                    isEnabled = state.isButtonActionEnable
                )
            }
        }
    }
}
