package com.washingtondcsquad.tudee.presentation.utils.modifierExensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dropShadow(
    shape: Shape,
    color: Color = Color.Black.copy(0.25f),
    blur: Dp = 4.dp,
    offsetY: Dp = 4.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
): Modifier = this.drawBehind {


    val shadowSize = Size(
        width = size.width + spread.toPx(),
        height = size.height + spread.toPx()
    )
    val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)


    val paint = Paint().apply {
        this.color = color
        if (blur.toPx() > 0) {
            asFrameworkPaint().maskFilter =
                android.graphics.BlurMaskFilter(
                    blur.toPx(),
                    android.graphics.BlurMaskFilter.Blur.NORMAL
                )
        }
    }

    drawIntoCanvas { canvas ->
        val spreadPx = spread.toPx() / 2f
        canvas.save()
        canvas.translate(
            offsetX.toPx() - spreadPx,
            offsetY.toPx() - spreadPx
        )
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}
