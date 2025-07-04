package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R

@Composable
fun CloudsOverlay(visible: Boolean) {
    Box(modifier = Modifier.fillMaxSize()) {

        val offsetRight by animateDpAsState(
            targetValue = if (visible) 0.dp else 40.dp,
            animationSpec = tween(durationMillis = 800),
            label = stringResource(R.string.offsetRight)
        )

        val offsetLeft by animateDpAsState(
            targetValue = if (visible) 0.dp else (-40).dp,
            animationSpec = tween(durationMillis = 800),
            label = stringResource(R.string.offsetLeft)
        )

        val alphaAnim by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = tween(durationMillis = 800),
            label = stringResource(R.string.alphaAnim)
        )

        Image(
            painter = painterResource(id = R.drawable.big_circle_gray),
            contentDescription = stringResource(R.string.big_circle_gray),
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
                .align(Alignment.TopEnd)
                .offset(x = 7.dp + offsetRight)
                .clip(CircleShape)
                .alpha(alphaAnim)
        )

        Image(
            painter = painterResource(id = R.drawable.big_circle_white),
            contentDescription = stringResource(R.string.big_circle_white),
            modifier = Modifier
                .size(29.dp)
                .align(Alignment.TopEnd)
                .offset(x = 7.dp + offsetLeft)
                .alpha(alphaAnim)
        )

        Image(
            painter = painterResource(id = R.drawable.small_circle_gray),
            contentDescription = stringResource(R.string.small_circle_gray),
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (-7).dp + offsetRight, y = 4.dp)
                .alpha(alphaAnim)
        )

        Image(
            painter = painterResource(id = R.drawable.small_circle_white),
            contentDescription = stringResource(R.string.small_circle_white),
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (-12).dp + offsetLeft, y = 4.dp)
                .alpha(alphaAnim)
        )

        Image(
            painter = painterResource(id = R.drawable.small_circle_white),
            contentDescription = stringResource(R.string.small_circle_white),
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.BottomEnd)
                .offset(x = offsetRight)
                .alpha(alphaAnim)
        )
    }
}
