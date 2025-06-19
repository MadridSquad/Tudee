package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.AppTheme.colors

@Composable
fun CancelableActionLayout(
    actionText: String,
    actionTextColor: Color,
    onAction: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
    actionBackgroundColor: List<Color>,
    isEnabled: Boolean = true,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Button(
            onClick = onAction,
            enabled = isEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = if (isEnabled) Brush.verticalGradient(actionBackgroundColor)
                    else Brush.verticalGradient(
                        listOf(
                            colors.disable,
                            colors.disable
                        )
                    ),
                    shape = RoundedCornerShape(100)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            contentPadding = PaddingValues(vertical = 16.dp),
            shape = RoundedCornerShape(100)
        ) {
            Text(
                text = actionText,
                color =  if (isEnabled) actionTextColor else colors.stroke,
                style = AppTheme.textStyle.label.large,
            )
        }

        Button(
            onClick = onCancel,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = AppTheme.colors.stroke,
                    shape = RoundedCornerShape(100)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = AppTheme.colors.primary,
            ),
            contentPadding = PaddingValues(vertical = 16.dp),
            shape = RoundedCornerShape(100)
        ) {
            Text(
                text = stringResource(R.string.cancel), style = AppTheme.textStyle.label.large,
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CancelableActionLayout(
        actionText = "Action",
        actionTextColor = Color.White,
        actionBackgroundColor = AppTheme.colors.primaryGradient,
        onAction = { },
        onCancel = { },
        modifier = Modifier,
        isEnabled = true
    )
}