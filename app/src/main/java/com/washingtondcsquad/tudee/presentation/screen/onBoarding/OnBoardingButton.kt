package com.washingtondcsquad.tudee.presentation.screen.onBoarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.utils.modifierExensions.dropShadow


@Composable
fun OnBoardingButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier
            .size(64.dp)
            .clip(
                RoundedCornerShape(
                    100.dp
                )
            )
            .background(
                brush = Brush.linearGradient(AppTheme.colors.primaryGradient)
            )
            .dropShadow(
                shape = RoundedCornerShape(
                    100.dp
                ), blur = 12.dp, offsetY = 4.dp, color = Color(0x1F000000)
            )
            .clickable {
                onClick()
            }) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_right_double),
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.Center),
            tint = AppTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
private fun OnBoardingButtonPrev() {
    AppTheme(true) {
        OnBoardingButton(onClick = {})
    }
}