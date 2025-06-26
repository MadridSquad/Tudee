package com.washingtondcsquad.tudee.presentation.features.tasks_screen.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.presentation.features.edit_task.EditTaskScreen

@Composable
fun ShowEditTaskScreen(
    showTaskDetails: MutableState<Boolean>,
    editTaskResult: MutableState<Pair<Boolean, String>>,
    taskId: TaskID
) {

    EditTaskScreen(
        taskId = taskId,
        onClickCancel = { showTaskDetails.value = false },
        onSuccessEdit = {
            showTaskDetails.value = false
            editTaskResult.value = true to "Task edited successfully"
        },
        onErrorEdit = {
            showTaskDetails.value = false
            editTaskResult.value = true to "Error editing task"
        },
    )
}