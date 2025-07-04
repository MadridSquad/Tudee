package com.washingtondcsquad.tudee.presentation.features.tasksScreen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(
    initialDateMillis: Long,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    onClear: () -> Long
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDateMillis
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Row(
                modifier = Modifier.padding(end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis = onClear()
                }
                ) {
                    TextButtonDatePicker(stringResource(R.string.tasks_screen_date_picker_clear))
                }
                Spacer(Modifier.weight(1f))
                TextButton(onClick = onDismiss) {
                    TextButtonDatePicker(stringResource(R.string.tasks_screen_date_picker_cancel))
                }
                TextButton(onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }) {
                    TextButtonDatePicker(stringResource(R.string.tasks_screen_date_picker_ok))
                }
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = AppTheme.colors.surface,
        )
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = AppTheme.colors.primary,
                selectedDayContentColor = AppTheme.colors.onPrimary,
                todayContentColor = AppTheme.colors.primary,
                todayDateBorderColor = AppTheme.colors.primary,
                dayContentColor = AppTheme.colors.title,
                containerColor = AppTheme.colors.surface,
            )
        )
    }
}