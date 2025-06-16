package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme
import com.washingtondcsquad.tudee.presentation.utils.modifierExensions.noRippleClick

@Composable
fun CategoryCard(
    title: String,
    iconPainter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    tasksCount: Int? = null
) {
    Column(
        modifier = modifier.noRippleClick(onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
        ) {
            Image(
                modifier = Modifier
                    .background(
                        color = AppTheme.colors.surfaceHigh,
                        shape = CircleShape
                    )
                    .padding(24.dp)
                    .size(32.dp),
                painter = iconPainter,
                contentDescription = title,

                )
            if (isSelected) {
                Icon(
                    painterResource(R.drawable.selection_icon),
                    contentDescription = null,
                    tint = AppTheme.colors.onPrimary,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(
                            color = AppTheme.colors.greenAccent,
                            shape = CircleShape
                        )
                        .padding(4.dp)
                )
            } else {
                tasksCount?.let { count ->
                    Text(
                        text = count.toString(),
                        color = AppTheme.colors.hint,
                        style = AppTheme.textStyle.label.small,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .background(
                                color = AppTheme.colors.surfaceLow,
                                shape = RoundedCornerShape(100)
                            )
                            .padding(horizontal = 12.dp, vertical = 2.dp)
                    )
                }
            }
        }
        Text(
            text = title,
            color = AppTheme.colors.body,
            style = AppTheme.textStyle.label.small
        )

    }

}


@Preview(showBackground = true)
@Composable
private fun Preview() {

    CategoryCard(
        title = "Test",
        iconPainter = painterResource(R.drawable.education_icon),
        onClick = {},
        modifier = Modifier,
        isSelected = false,
        tasksCount = 3
    )
}