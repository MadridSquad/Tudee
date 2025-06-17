package com.washingtondcsquad.tudee.presentation.utils.modifierExensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun statusColor(status: TaskStatus): Color {
    return when (status) {
        TaskStatus.TODO -> AppTheme.colors.yellowAccent.copy(0.15f)
        TaskStatus.IN_PROGRESS -> AppTheme.colors.purpleAccent.copy(0.15f)
        TaskStatus.DONE -> AppTheme.colors.greenAccent.copy(0.15f)
    }
}
