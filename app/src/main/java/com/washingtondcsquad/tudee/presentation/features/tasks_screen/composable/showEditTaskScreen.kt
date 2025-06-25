package com.washingtondcsquad.tudee.presentation.features.tasks_screen.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.washingtondcsquad.tudee.presentation.screens.add_task.EditTaskScreen

@Composable
fun ShowEditTaskScreen(
    showTaskDetails: MutableState<Boolean>,
    editTaskResult: MutableState<Pair<Boolean, String>>,
    taskId: Int
) {
    EditTaskScreen(
        onCancelAddTaskBottomSheet = { showTaskDetails.value = false },
        onActionResult = { a, b ->
            editTaskResult.value = a to b
        },
        taskId = taskId,
    )
}