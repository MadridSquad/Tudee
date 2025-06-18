package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.AppTheme.textStyle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size


@Composable
fun AppTextField(
    prefixIconPainter: Painter?,
    hintText: String,
    onValueChange: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    onPrefixIconClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isFocused by interactionSource.collectIsFocusedAsState()

    TextField(
        readOnly = readOnly,
        interactionSource = interactionSource,
        value = value,
        textStyle = AppTheme.textStyle.label.large,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hintText,
                color = AppTheme.colors.hint,
                style = textStyle.label.medium,
            )
        },
        shape = RoundedCornerShape(16.dp),
        leadingIcon = if (prefixIconPainter != null) {
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = prefixIconPainter,
                        contentDescription = null,
                        tint = AppTheme.colors.hint,
                        modifier = if (onPrefixIconClick != null) {
                            Modifier.size(24.dp).clickable { onPrefixIconClick() }
                        } else {
                            Modifier.size(24.dp)
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(32.dp)
                            .background(AppTheme.colors.stroke)
                    )
                }
            }
        } else null,

        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = if (value.isEmpty()) AppTheme.colors.hint else AppTheme.colors.body,
            focusedTextColor = AppTheme.colors.body,
        ),
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (isFocused) AppTheme.colors.primary else AppTheme.colors.stroke,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp),
    )

}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    val text = remember { mutableStateOf("") }
    AppTextField(
        prefixIconPainter = painterResource(R.drawable.education_icon),
        hintText = "Email",
        onValueChange = {
            text.value = it
        },
        value = text.value,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}