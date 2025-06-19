package com.washingtondcsquad.tudee.presentation.screens.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.getPriorityIcon
import com.washingtondcsquad.tudee.presentation.components.AppTextField
import com.washingtondcsquad.tudee.presentation.components.CancelableActionLayout
import com.washingtondcsquad.tudee.presentation.components.CategoryCard
import com.washingtondcsquad.tudee.presentation.components.DatePickerModal
import com.washingtondcsquad.tudee.presentation.components.TaskPriorityCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle
import com.washingtondcsquad.tudee.presentation.screens.composable.getBackgroundColor
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewTaskScreen(
    onCancelAddTaskBottomSheet: () -> Unit,
    taskDate: LocalDate = LocalDate.now(),
    viewModel: AddTaskViewModel = koinViewModel(
        parameters = { parametersOf(taskDate) }
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
                        viewModel::onHideDatePicker
                    },
                    onDismiss = viewModel::onHideDatePicker
                )
            }

            Column(
                Modifier.fillMaxHeight(0.8f)
            ) {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f)

                ) {
                    item {
                        Text(
                            text = stringResource(R.string.add_new_task),
                            modifier = Modifier.offset(y = 4.dp),
                            style = defaultTextStyle.title.large
                        )
                    }

                    item {
                        AppTextField(
                            prefixIconPainter = painterResource(R.drawable.add_task_title),
                            hintText = stringResource(R.string.task_title_hint),
                            value = state.taskTitle,
                            onValueChange = { viewModel.onTitleChange(it) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)

                        )
                    }

                    item {
                        AppTextField(
                            prefixIconPainter = null,
                            hintText = stringResource(R.string.description_hint),
                            value = state.taskDescription,
                            onValueChange = { viewModel.onDescriptionChange(it) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(168.dp)
                        )
                    }

                    item {
                        val dateFormatter = DateTimeFormatter.ofPattern( stringResource(R.string.date_format) )
                        val formattedDate =
                            state.taskDate.format(dateFormatter)
                        AppTextField(
                            prefixIconPainter = painterResource(R.drawable.add_task_calendar),
                            hintText = formattedDate,
                            value = formattedDate,
                            onValueChange = { },
                            onPrefixIconClick = viewModel::onShowDatePicker,
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }


                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(R.string.priority) ,
                                style = defaultTextStyle.title.medium,
                                color = AppTheme.colors.title
                            )
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                items(state.priorityList) { priority ->
                                    TaskPriorityCard(
                                        icon = painterResource(getPriorityIcon(priority)),
                                        title = priority.name,
                                        backgroundColor =  getBackgroundColor(priority),
                                        isSelected = state.selectedPriority == priority,
                                        onClick = { viewModel.onPrioritySelected(priority) },
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(R.string.category) ,
                                style = defaultTextStyle.title.medium,
                                color = AppTheme.colors.title
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Column(
                                verticalArrangement = Arrangement.spacedBy(24.dp)
                            ) {
                                state.categoryList.chunked(3).forEach { rowCategories ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        rowCategories.forEach { category ->
                                            CategoryCard(
                                                title = category.title,
                                                iconPainter = painterResource(R.drawable.education_icon),
                                                onClick = { viewModel.onCategorySelected(category) },
                                                isSelected = state.selectedCategory == category,
                                                modifier = Modifier.weight(1f)
                                            )
                                        }
                                        repeat(3 - rowCategories.size) {
                                            Spacer(modifier = Modifier.weight(1f))
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

                CancelableActionLayout(
                    modifier = Modifier,
                    actionText = stringResource(R.string.add),
                    actionTextColor = Color.White,
                    actionBackgroundColor = AppTheme.colors.primaryGradient,
                    onAction = viewModel::onClickSaveButton ,
                    onCancel = onCancelAddTaskBottomSheet,
                    isEnabled = state.isButtonActionEnable
                )
            }
        }
    }
}

//@Preview
@Composable
fun Preview() {
    AddNewTaskScreen(
        onCancelAddTaskBottomSheet = {}
    )
}
