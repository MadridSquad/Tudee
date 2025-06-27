package com.washingtondcsquad.tudee.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
@Composable
fun TaskStatus.toDisplayName(): String = when (this) {
    TaskStatus.TODO -> stringResource(R.string.to_do)
    TaskStatus.IN_PROGRESS -> stringResource(R.string.in_progress_title)
    TaskStatus.DONE -> stringResource(R.string.done)
}

fun TaskStatus.next(): TaskStatus = when (this) {
    TaskStatus.TODO -> TaskStatus.IN_PROGRESS
    TaskStatus.IN_PROGRESS -> TaskStatus.DONE
    TaskStatus.DONE -> TaskStatus.DONE
}