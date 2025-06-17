package com.washingtondcsquad.tudee.presentation.screen.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme


@Composable
fun OnboardingPager(
    modifier: Modifier, pagerState: PagerState, pages: List<OnboardingPage>, onNextClick: () -> Unit
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
    ) { pageIndex ->
        val page = pages[pageIndex]
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = page.imageResId),
                contentDescription = null,
                modifier = Modifier.height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.size(36.dp))
            Box(
                modifier = Modifier
                    .height(219.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(192.dp)
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = AppTheme.colors.onPrimaryStroke,
                            shape = RoundedCornerShape(size = 32.dp)
                        )
                        .background(
                            color = AppTheme.colors.onPrimaryCard,
                            shape = RoundedCornerShape(size = 32.dp)
                        )
                        .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 48.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(page.titleResId),
                            style = AppTheme.textStyle.title.medium,
                            color = AppTheme.colors.title,
                            textAlign = TextAlign.Center


                        )
                        Spacer(Modifier.size(16.dp))
                        Text(
                            text = stringResource(page.descriptionResId),
                            style = AppTheme.textStyle.body.medium,
                            color = AppTheme.colors.body,
                            textAlign = TextAlign.Center

                        )

                    }
                }
                OnBoardingButton(
                    modifier = Modifier.align(Alignment.BottomCenter), onClick = { onNextClick() })

            }
        }

    }
}
