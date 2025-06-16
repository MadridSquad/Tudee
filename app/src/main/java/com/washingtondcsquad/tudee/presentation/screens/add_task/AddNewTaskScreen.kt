package com.washingtondcsquad.tudee.presentation.screens.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.AppTextField
import com.washingtondcsquad.tudee.presentation.components.CancelableActionLayout
import com.washingtondcsquad.tudee.presentation.components.TaskPriorityCard
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle
import org.koin.androidx.compose.koinViewModel

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddNewTaskScreen(
    viewModel: AddTaskViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.errorShown()
        }
    }

    ModalBottomSheet(
        onDismissRequest = {  },
    ) {

        Scaffold(
            bottomBar = {
            CancelableActionLayout(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                actionText = "Add Task",
                actionTextColor = Color.White,
                actionBackgroundColor = AppTheme.colors.primaryGradient,
                onAction = { viewModel.saveTask() },
                onCancel = { },
            )
        }

        ) { scaffoldPadding ->

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(horizontal = 16.dp)

            ) {
                item {
                    Text(
                        text = "Add New Task",
                        modifier = Modifier.offset(y = 4.dp),
                        style = defaultTextStyle.title.large
                    )
                }

                // Title
                item {
                    AppTextField(
                        prefixIconPainter = painterResource(R.drawable.flag_icon),
                        hintText = "Task Title",
                        value = uiState.taskTitle,
                        onValueChange = viewModel::onTitleChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Description
                item {
                    AppTextField(
                        prefixIconPainter = null,
                        hintText = "Description",
                        value = uiState.taskDescription,
                        onValueChange = viewModel::onDescriptionChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(168.dp)
                    )
                }

                // todo Calendar
                item {
                    AppTextField(
                        prefixIconPainter = painterResource(R.drawable.flag_icon),
                        hintText = "Calendar",
                        value = "",
                        onValueChange = { },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "priority",
                            style = defaultTextStyle.title.medium,
                            color = AppTheme.colors.title
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            TaskPriorityCard(
                                icon = painterResource(id = R.drawable.flag_icon),
                                title = "High",
                                backgroundColor = AppTheme.colors.pinkAccent,
                            )

                        }
                    }
                }

                item {
                    Column {
                        Text(text = "Category")

                    }
                }
            }

        }
    }
    }