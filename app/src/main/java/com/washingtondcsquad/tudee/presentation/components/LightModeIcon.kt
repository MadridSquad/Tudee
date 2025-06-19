package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


@Composable
fun LightModeIcon(visible: Boolean) {
    val alphaAnim by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "sunAlpha"
    )

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alphaAnim)
    ) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFF2C849), Color(0xFFF49061)),
                radius = size.minDimension / 2
            ),
            radius = size.minDimension / 2,
            center = center
        )
    }
}


