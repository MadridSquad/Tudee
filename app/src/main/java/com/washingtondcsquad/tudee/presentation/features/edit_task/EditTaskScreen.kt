package com.washingtondcsquad.tudee.presentation.features.edit_task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.presentation.components.CancelableActionLayout
import com.washingtondcsquad.tudee.presentation.components.DatePickerModal
import com.washingtondcsquad.tudee.presentation.components.TaskScreenComponents
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.EditTaskUiState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    taskId: TaskID,
    onClickCancel: () -> Unit,
    onSuccessEdit: (message: String) -> Unit,
    onErrorEdit: (message: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditTaskViewModel = koinViewModel(
        parameters = { parametersOf(taskId) }
    ),
) {
    val state by viewModel.state.collectAsState()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    EditTaskContent(
        state = state,
        bottomSheetState = bottomSheetState,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onDateSelected = viewModel::onDateSelected,
        onShowDatePicker = viewModel::onShowDatePicker,
        onHideDatePicker = viewModel::onHideDatePicker,
        onPrioritySelected = viewModel::onPrioritySelected,
        onCategorySelected = viewModel::onCategorySelected,
        onClickUpdate = viewModel::onClickEditButton,
        onClickCancel = onClickCancel,
        onSuccessEdit = onSuccessEdit,
        onErrorEdit = onErrorEdit,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditTaskContent(
    state: EditTaskUiState,
    bottomSheetState: SheetState,
    onTitleChange: (newTitle: String) -> Unit,
    onDescriptionChange: (newDescription: String) -> Unit,
    onDateSelected: (dateAsMilliseconds: Long) -> Unit,
    onShowDatePicker: () -> Unit,
    onHideDatePicker: () -> Unit,
    onPrioritySelected: (priority: com.washingtondcsquad.tudee.domain.entity.Priority) -> Unit,
    onCategorySelected: (category: com.washingtondcsquad.tudee.domain.entity.Category) -> Unit,
    onClickUpdate: (onSuccess: (message: String) -> Unit, onError: (message: String) -> Unit) -> Unit,
    onClickCancel: () -> Unit,
    onSuccessEdit: (message: String) -> Unit,
    onErrorEdit: (message: String) -> Unit,
    modifier: Modifier = Modifier
) {
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
                header = stringResource(R.string.edit_task),
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
                    onClickUpdate(onSuccessEdit, onErrorEdit)
                },
                onCancel = {
                    onClickCancel()
                },
                isEnabled = state.isButtonActionEnable
            )
        }
    }
}