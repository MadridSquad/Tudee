package com.washingtondcsquad.tudee.presentation.screens.tasksScreen.composable

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
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    initialDateMillis: Long,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    onClear: () -> Long
) {

    var datePickerState = rememberDatePickerState(
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
                    TextButtonDatePicker("Clear")
                }
                Spacer(Modifier.weight(1f))
                TextButton(onClick = onDismiss) {
                    TextButtonDatePicker("Cancel")
                }
                TextButton(onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }) {
                    TextButtonDatePicker("OK")
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