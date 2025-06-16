package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

@Composable
fun DarkModeIcon(visible: Boolean) {
    val moonAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "moonAlpha"
    )

    val cratersAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "craterAlpha"
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val moonRadius = size.minDimension / 2
        val moonCenter = center

        // 🌘 Shadow behind the moon
        drawCircle(
            color = Color(0xFF323297).copy(alpha = 0.4f), // أغمق وظاهر
            radius = moonRadius + 40f, // أكبر أكتر عشان يبان
            center = moonCenter,
            alpha = moonAlpha
        )

        // 🌕 Moon with alpha
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFfE0E9FE), Color(0xFFE9F0FF))
            ),
            radius = moonRadius,
            center = moonCenter,
            alpha = moonAlpha
        )

        // 🌑 Craters with different fade timing
        drawCraterWithShadow(
            center = Offset(moonCenter.x - 3f, moonCenter.y - 20f),
            radius = 8f,
            alpha = cratersAlpha
        )
        drawCraterWithShadow(
            center = Offset(moonCenter.x - 13f, moonCenter.y + 8f),
            radius = 14f,
            alpha = cratersAlpha
        )
        drawCraterWithShadow(
            center = Offset(moonCenter.x + 12f, moonCenter.y + 20f),
            radius = 4f,
            alpha = cratersAlpha
        )
    }
}


fun DrawScope.drawCraterWithShadow(center: Offset, radius: Float, alpha: Float) {
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(
                Color(0xFFE9EFFF),
                Color(0xffBFD2FF)
            ),
            center = Offset(center.x + radius / 3, center.y + radius / 3),
            radius = radius
        ),
        radius = radius,
        center = center,
        alpha = alpha
    )
}
