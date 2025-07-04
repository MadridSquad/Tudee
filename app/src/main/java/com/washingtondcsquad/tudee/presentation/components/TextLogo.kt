package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.Cherry_Bomb


@Composable
fun TextLogo(title: String, size: Int, stroke: Float = 15f
) {
    Box {
        Text(
            text = title,
            fontSize = size.sp,
            fontWeight = FontWeight.W400,
            fontFamily = Cherry_Bomb,
            color = AppTheme.colors.primary,
            style = TextStyle(
                drawStyle = Stroke(
                    width = stroke
                )
            )
        )
        Text(
            text = title,
            fontSize = size.sp,
            fontWeight = FontWeight.W400,
            fontFamily = Cherry_Bomb,
            color = AppTheme.colors.onPrimary,
        )

    }
}