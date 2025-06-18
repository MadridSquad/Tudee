package com.washingtondcsquad.tudee.presentation.screens.add_task

import com.washingtondcsquad.tudee.presentation.components.DatePickerModal
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.AppTextField
import com.washingtondcsquad.tudee.presentation.components.TaskPriorityCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import com.washingtondcsquad.tudee.presentation.components.CancelableActionLayout
import com.washingtondcsquad.tudee.presentation.components.CategoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewTaskScreen(
    viewModel: AddTaskViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        ModalBottomSheet(
            onDismissRequest = { },
            containerColor = Color(0xFFF9F9F9),
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true // <-- skip middle state
            ),
        ) {
                if (uiState.isDatePickerDisplayed) {
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
                    //.padding(scaffoldPadding)
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f)

                ) {

                    // fixed Text (Add New Task)
                    item {
                        Text(
                            text = "Add New Task",
                            modifier = Modifier.offset(y = 4.dp),
                            style = defaultTextStyle.title.large
                        )
                    }

                    // title
                    item {
                        AppTextField(
                            prefixIconPainter = painterResource(R.drawable.flag_icon),
                            hintText = "Task Title",
                            value = uiState.taskTitle,
                            onValueChange = { viewModel.onTitleChange(it) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // description
                    item {
                        AppTextField(
                            prefixIconPainter = null,
                            hintText = "Description",
                            value = uiState.taskDescription,
                            onValueChange = { viewModel.onDescriptionChange(it) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(168.dp)
                        )
                    }

                    // calendar
                    item {
                        val dateFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
                        val currentDate = uiState.taskDate
                        val formattedDate = currentDate?.format(dateFormatter) ?: "Calendar"
                        AppTextField(
                            prefixIconPainter = painterResource(R.drawable.calendar_icon),
                            hintText = "Calendar",
                            value = formattedDate,
                            onValueChange = { },

                            onPrefixIconClick = viewModel::onShowDatePicker,
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // priority
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Priority",
                                style = defaultTextStyle.title.medium,
                                color = AppTheme.colors.title
                            )
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                items(viewModel.uiState.value.priorityList) { priority ->
                                    TaskPriorityCard(
                                        priority = priority,
                                        isSelected = uiState.selectedPriority == priority,
                                        onClick = { viewModel.onPrioritySelected(priority) },
                                    )
                                }
                            }
                        }
                    }

                    // category
                    item {
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Category",
                                style = defaultTextStyle.title.medium,
                                color = AppTheme.colors.title
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Column(
                                verticalArrangement = Arrangement.spacedBy(24.dp)
                            ) {
                                uiState.categoryList.chunked(3).forEach { rowCategories ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        rowCategories.forEach { category ->
                                            CategoryCard(
                                                title = category.title,
                                                iconPainter = painterResource(R.drawable.education_icon),
                                                onClick = { viewModel.onCategorySelected(category) },
                                                isSelected = uiState.selectedCategory == category,
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
                    // .padding(horizontal = 16.dp, vertical = 16.dp),
                    actionText = "Add Task",
                    actionTextColor = Color.White,
                    actionBackgroundColor = AppTheme.colors.primaryGradient,
                    onAction = { viewModel::onClickSaveButton},
                    onCancel = { },
                    isEnabled = uiState.isButtonActionEnable
                )
            }

        }

    }


}


//@Preview
@Composable
fun Preview() {
    AddNewTaskScreen()
}
