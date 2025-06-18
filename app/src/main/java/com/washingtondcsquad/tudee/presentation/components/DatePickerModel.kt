package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        colors = DatePickerDefaults.colors(
            selectedDayContainerColor = AppTheme.colors.primary,
            selectedDayContentColor = AppTheme.colors.primary  ,
            todayContentColor = AppTheme.colors.onPrimary,
            dayContentColor = AppTheme.colors.primary,
            headlineContentColor =AppTheme.colors.primary,
            containerColor = AppTheme.colors.onPrimary
        ),
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK",
                    style = defaultTextStyle.label.large,
                    color = AppTheme.colors.primary
                    )
            }
        },
        dismissButton = {

            Row{
                TextButton(onClick = onDismiss) {
                    Text("Clear",
                        style = defaultTextStyle.label.large,
                        color = AppTheme.colors.primary
                    )

                }
                Spacer(Modifier.width(150.dp))
                TextButton(onClick = onDismiss) {
                    Text("Cancel",
                        style = defaultTextStyle.label.large,
                        color = AppTheme.colors.primary
                    )
                }
            }



        }

    ) {
        DatePicker(state = datePickerState)
    }
}
@Composable
@Preview
fun PickerPreview(){
    DatePickerModal({}) { }
}