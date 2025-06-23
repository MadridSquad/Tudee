package com.washingtondcsquad.tudee.presentation.features.onBoarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun OnBoardingPagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.wrapContentHeight()
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val targetColor =
                if (pagerState.currentPage == iteration) AppTheme.colors.primary else AppTheme.colors.primaryVarient
            val color by animateColorAsState(
                targetValue = targetColor,
                label = "PagerIndicatorColor"
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .height(5.dp)
                    .weight(1f)
                    .background(color)

            )
        }
    }
}

@Preview
@Composable
private fun OnBoardingPagerIndicatorPrev() {
    Box(contentAlignment = Alignment.Center) {
        AppTheme(true) {
            OnBoardingPagerIndicator(
                pagerState = rememberPagerState(
                    initialPage = 0,
                    pageCount = { 3 }
                )
            )

        }
    }
}