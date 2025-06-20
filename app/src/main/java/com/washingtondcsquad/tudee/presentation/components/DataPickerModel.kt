package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Typography
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.washingtondcsquad.tudee.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,

) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    MaterialTheme (
        typography = Typography(
            headlineLarge = defaultTextStyle.headLine.large,
            titleLarge = defaultTextStyle.label.large
        ),
        colorScheme = MaterialTheme.colorScheme.copy(
            surface = AppTheme.colors.surface,
            onSurface = AppTheme.colors.title,
            primary = AppTheme.colors.primary,
            onPrimary = AppTheme.colors.onPrimary,
        )
    ){

        DatePickerDialog(
            onDismissRequest = onDismiss,
            colors = DatePickerDefaults.colors(
                containerColor = AppTheme.colors.surface
            ),
            confirmButton = {
                TextButton(onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }) {
                    Text(
                        stringResource(id = R.string.ok),
                        style = defaultTextStyle.label.large,
                        color = AppTheme.colors.primary,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            },
            dismissButton = {
                Row{
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis = null
                        onDismiss()

                    }) {
                        Text(
                            text = stringResource(id = R.string.clear),
                            style = defaultTextStyle.label.large,
                            color = AppTheme.colors.primary
                        )

                    }
                    Spacer(Modifier.width(145.dp))
                    TextButton(onClick = onDismiss) {
                        Text(
                            stringResource(id = R.string.cancel),
                            style = defaultTextStyle.label.large,
                            color = AppTheme.colors.primary
                        )
                    }
                }

            }

        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = AppTheme.colors.primary,
                    selectedDayContentColor = AppTheme.colors.onPrimary  ,
                    todayContentColor = AppTheme.colors.primary,
                    dayContentColor = AppTheme.colors.title,
                    todayDateBorderColor = AppTheme.colors.primary,
                    containerColor = AppTheme.colors.surface,
                    selectedYearContentColor = AppTheme.colors.onPrimary,
                    selectedYearContainerColor = AppTheme.colors.primary,
                    yearContentColor = AppTheme.colors.body,
                    subheadContentColor = AppTheme.colors.title,
                    headlineContentColor = AppTheme.colors.title,
                )
            )
        }

    }
}

@Composable
@Preview
fun PickerPreview(){

  DatePickerModal({}) { }
}