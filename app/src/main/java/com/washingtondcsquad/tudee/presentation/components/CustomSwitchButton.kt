package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme


@Composable
fun CustomSwitchButton(
    switchPadding: Dp,
    buttonWidth: Dp,
    buttonHeight: Dp,
    value: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val switchSize = buttonHeight - switchPadding * 2

    val interactionSource = remember { MutableInteractionSource() }

    var switchClicked by remember { mutableStateOf(value) }

    val padding by animateDpAsState(
        targetValue = if (switchClicked) buttonWidth - switchSize - switchPadding * 2 else 0.dp,
        animationSpec = tween(
            durationMillis = 700,
            easing = LinearOutSlowInEasing
        ), label = "padding"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (switchClicked) AppTheme.colors.darkSwitch else AppTheme.colors.primary,
        animationSpec = tween(700),
        label = "backgroundColor"
    )

    Box(
        modifier = Modifier
            .width(buttonWidth)
            .height(buttonHeight)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                switchClicked = !switchClicked
                onToggle(switchClicked)
            },

    ) {

        // ⭐ Show stars if in dark mode
        if (switchClicked) {
            StarsOverlay()
        }

        // ☁️ Clouds in light mode
        CloudsOverlay(visible = !switchClicked)

        Row(modifier = Modifier.fillMaxSize().padding(switchPadding)) {

            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(padding)
            )

            Box(
                modifier = Modifier
                    .size(switchSize)
                    .clip(CircleShape)
            ) {
                LightModeIcon(visible = !switchClicked)
                DarkModeIcon(visible = switchClicked)
            }
        }
    }
}

