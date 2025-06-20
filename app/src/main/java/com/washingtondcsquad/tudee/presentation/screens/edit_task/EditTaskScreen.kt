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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.AppTextField
import com.washingtondcsquad.tudee.presentation.components.CancelableActionLayout
import com.washingtondcsquad.tudee.presentation.components.CategoryCard
import com.washingtondcsquad.tudee.presentation.components.DatePickerModal
import com.washingtondcsquad.tudee.presentation.components.TaskPriorityCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.ImageSource
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    onCancelAddTaskBottomSheet: () -> Unit,
    onActionResult : (success:Boolean,message:String) ->Unit ,
    taskId:Int,
    viewModel: EditTaskViewModel = koinViewModel(
        parameters = {
            parametersOf(
                taskId,
                onCancelAddTaskBottomSheet,
                onActionResult
            )
        }
    ),
) {
    val drawablesOfCategories = remember {
        listOf(
            R.drawable.education_icon,
            R.drawable.shopping,
            R.drawable.medical,
            R.drawable.gym,
            R.drawable.entertainment,
            R.drawable.cooking,
            R.drawable.family,
            R.drawable.traveling,
            R.drawable.agriculture,
            R.drawable.coding,
            R.drawable.adoration,
            R.drawable.fix_bug,
            R.drawable.cleaning,
            R.drawable.work,
            R.drawable.budgeting,
            R.drawable.self_care,
            R.drawable.event
        )
    }
    val titlesOfCategories = remember {  listOf(
        "Education",
        "Shopping",
        "Medical",
        "Gym",
        "Entertainment",
        "Cooking",
        "Family & friend",
        "Traveling",
        "Agriculture",
        "Coding",
        "Adoration",
        "Fix bug",
        "Cleaning",
        "Work",
        "Budgeting",
        "Self care",
        "Event"
    ) }
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
                    onDateSelected = {selectedDateMillis->
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

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f)

                ) {
                    item {
                        Text(
                            text = stringResource(R.string.edit_task),
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
                        AppTextField(
                            prefixIconPainter = painterResource(R.drawable.add_task_calendar),
                            hintText = state.taskDate,
                            value = state.taskDate,
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
                                        priority = priority,
                                        isSelected = state.selectedPriority == priority,
                                        onClick = { viewModel.onPrioritySelected(priority) },
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            text = stringResource(R.string.category),
                            style = defaultTextStyle.title.medium,
                            color = AppTheme.colors.title,
                            modifier = Modifier.padding(top = 2.dp , bottom = 8.dp)
                        )
                        val rows = state.categoryList.chunked(3)
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            rows.forEach { rowCategories ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    rowCategories.forEach { category ->
                                        val index = state.categoryList.indexOf(category)

                                        val imageSource = if (index < drawablesOfCategories.size) {
                                            ImageSource.Drawable(drawablesOfCategories[index])
                                        } else {
                                            ImageSource.Path(category.iconPath)
                                        }

                                        val title = if (index < titlesOfCategories.size) {
                                            titlesOfCategories[index]
                                        } else {
                                            category.title
                                        }

                                        CategoryCard(
                                            title = title,
                                            imageSource = imageSource,
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

                CancelableActionLayout(
                    modifier = Modifier,
                    actionText = stringResource(R.string.save),
                    actionTextColor = Color.White,
                    actionBackgroundColor = AppTheme.colors.primaryGradient,
                    onAction = viewModel::onClickEditButton ,
                    onCancel = onCancelAddTaskBottomSheet,
                    isEnabled = state.isButtonActionEnable
                )
            }
        }
    }
}

//@Preview
@Composable
fun EditTaskPreview() {
//    AddNewTaskScreen(
//        onCancelAddTaskBottomSheet = {}
//    )
}
