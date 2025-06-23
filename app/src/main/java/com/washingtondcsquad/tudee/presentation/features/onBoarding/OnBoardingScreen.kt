package com.washingtondcsquad.tudee.presentation.features.onBoarding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState(
        initialPage = state.currentPage, pageCount = {
            state.pages.size
        })
    LaunchedEffect(pagerState.currentPage) {
        viewModel.onPageChanged(pagerState.currentPage)
    }
    val scope = rememberCoroutineScope()

    OnBoardingScreenContent(
        modifier = modifier,
        uiState = state,
        onNextClick = {
            if (state.isLastPage) {
                viewModel.onboardingFinished()
                onFinish()
            } else {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        },
        onSkipClick = {
            scope.launch {
                val lastPage = state.pages.size - 1
                pagerState.animateScrollToPage(lastPage)
            }
        },
        pagerState = pagerState
    )
}

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun OnBoardingScreenContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    uiState: OnboardingUiState,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Box(
        modifier
            .fillMaxSize()
            .background(AppTheme.colors.overlay)
    ) {
        AnimatedContent(pagerState.currentPage != pagerState.pageCount - 1) {
            if (it) {
                Text(
                    text = "Skip",
                    style = AppTheme.textStyle.label.large,
                    color = AppTheme.colors.primary,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp, start = 16.dp)
                        .clickable {
                            onSkipClick()
                        })

            }
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            OnboardingPager(
                modifier = Modifier, pagerState = pagerState, pages = uiState.pages,
                onNextClick = { onNextClick() }
            )
            Spacer(Modifier.size(32.dp))
            OnBoardingPagerIndicator(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 24.dp),
                pagerState = pagerState,
            )
        }


    }
}