package com.washingtondcsquad.tudee.presentation.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.CustomSwitchButton
import com.washingtondcsquad.tudee.presentation.components.TaskCard
import com.washingtondcsquad.tudee.presentation.components.TextLogo
import com.washingtondcsquad.tudee.presentation.components.analytics_components.AnalyticsCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.screens.add_task.AddNewTaskScreen
import com.washingtondcsquad.tudee.presentation.utils.SetStatusBarIconsColor
import com.washingtondcsquad.tudee.presentation.utils.modifierExensions.noRippleClick
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    SetStatusBarIconsColor(false)
    HomeScreenContent(modifier, state, viewModel)

}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    viewModel: HomeViewModel
) {
    val isEmptyState =
        state.inProgressTasks.isEmpty() and state.todoTasks.isEmpty() and state.doneTasks.isEmpty()
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = AppTheme.colors.primary
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .align(Alignment.BottomCenter)
                .background(
                    color = AppTheme.colors.surface
                )
        )
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.tudee_home_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .background(
                                color = Color.White.copy(.4f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = Color.White.copy(.4f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(8.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        TextLogo(title = stringResource(R.string.app_name), size = 18)
                        Text(
                            text = stringResource(R.string.home_app_bar_subtitle),
                            color = AppTheme.colors.onPrimaryCaption,
                            style = AppTheme.textStyle.label.small,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    CustomSwitchButton(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 16.dp),
                        switchPadding = 8.dp,
                        buttonWidth = 64.dp,
                        buttonHeight = 36.dp,
                        isDarkTheme = false,
                        onToggle = { viewModel.onThemeSwitched(it) }
                    )
                }
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                    AnalyticsCard(
                        doneCount = state.doneTasks.size,
                        inProgressCount = state.inProgressTasks.size,
                        toDoCount = state.todoTasks.size,
                        tudeeStatus = state.tudeeStatus,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    )

                    if (isEmptyState) {
                        NoTasksPlaceHolder(modifier = Modifier.padding(top = 48.dp))
                    } else {
                        if (state.inProgressTasks.isNotEmpty())
                            TaskStatusLayout(
                                tasks = state.inProgressTasks,
                                title = stringResource(R.string.in_progress_title),
                                modifier = Modifier
                                    .padding(top = 16.dp),
                                onTaskClick = viewModel::onTaskClicked,
                                onSeeMoreClick = {}
                            )
                        if (state.todoTasks.isNotEmpty())
                            TaskStatusLayout(
                                tasks = state.todoTasks,
                                title = stringResource(R.string.to_do_title),
                                modifier = Modifier.padding(top = 24.dp),
                                onTaskClick = viewModel::onTaskClicked,
                                onSeeMoreClick = {}
                            )

                    }
                }


            }
        }

        if (showBottomSheet) {
            AddNewTaskScreen(
                onCancelAddTaskBottomSheet = {
                    showBottomSheet = false
                }
            )
        }
        FabIcon(
            modifier = Modifier
                .clickable {
                    showBottomSheet = true
                }
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
private fun FabIcon(modifier: Modifier) {
    Icon(
        painter = painterResource(R.drawable.note_add_icon),
        contentDescription = null,
        tint = AppTheme.colors.onPrimary,
        modifier = modifier
            .padding(end = 16.dp, bottom = 16.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                clip = false,
            )
            .background(
                brush = Brush.linearGradient(AppTheme.colors.primaryGradient),
                shape = CircleShape
            )
            .padding(18.dp)
            .size(28.dp)
    )
}

@Composable
private fun TaskStatusLayout(
    title: String,
    tasks: List<TaskUiState>,
    onTaskClick: (Int) -> Unit,
    onSeeMoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = remember {
        tasks.shuffled().toMutableStateList()
    }
    val numberOfItemPerRow = 3
    val widthPx = LocalWindowInfo.current.containerSize.width
    val density = LocalDensity.current
    val widthDp = with(density) { widthPx.toDp() }
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = AppTheme.colors.title,
                style = AppTheme.textStyle.title.large,
                modifier = Modifier.padding(start = 16.dp)
            )

            Row(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .background(
                        color = AppTheme.colors.surfaceHigh,
                        shape = RoundedCornerShape(100)
                    )
                    .noRippleClick(onSeeMoreClick)
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tasks.size.toString(),
                    color = AppTheme.colors.body,
                    style = AppTheme.textStyle.label.small,
                )
                Icon(
                    painter = painterResource(R.drawable.forward_arrow_icon),
                    contentDescription = null,
                    tint = AppTheme.colors.body,
                    modifier = Modifier.size(12.dp)
                )
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.take(numberOfItemPerRow)) { item ->
                TaskCard(
                    taskUiState = item,
                    onTaskClicked = onTaskClick,
                    modifier = Modifier.width(widthDp.minus(40.dp))
                )
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.drop(numberOfItemPerRow).take(numberOfItemPerRow)) { item ->
                TaskCard(
                    taskUiState = item,
                    onTaskClicked = onTaskClick,
                    modifier = Modifier.width(widthDp.minus(40.dp))
                )
            }
        }
    }
}


@Composable
private fun NoTasksPlaceHolder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp, bottomStart = 16.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .shadow(
                    elevation = 4.dp,
                    shape = shape
                )
                .background(
                    color = AppTheme.colors.surfaceHigh,
                    shape = shape
                )
                .padding(vertical = 8.dp, horizontal = 12.dp),

            ) {
            Text(
                text = stringResource(R.string.no_tasks_for_today),
                color = AppTheme.colors.body,
                style = AppTheme.textStyle.title.small,
            )
            Text(
                text = stringResource(R.string.tap_plus_to_add_task),
                color = AppTheme.colors.hint,
                style = AppTheme.textStyle.body.small,
            )
        }
        Image(
            painter = painterResource(id = R.drawable.empty_tasks_palceholder_background),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )
        Image(
            painter = painterResource(id = R.drawable.empty_tasks_palce_holder_image),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(107.dp)
        )

    }

}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    HomeScreenContent(
        modifier = Modifier,
        state = HomeUiState(),
        viewModel = koinViewModel<HomeViewModel>()
    )
}