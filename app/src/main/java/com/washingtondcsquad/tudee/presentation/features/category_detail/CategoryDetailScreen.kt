package com.washingtondcsquad.tudee.presentation.features.category_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.SnackbarHandler
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.ImageSource.AddedByUser
import com.washingtondcsquad.tudee.presentation.components.TaskCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.category_detail.components.CategoryDetailTopAppBar
import com.washingtondcsquad.tudee.presentation.features.category_detail.components.EditCategoryBottomSheet
import com.washingtondcsquad.tudee.presentation.features.category_detail.components.NoTaskInCategoryPlaceHolder
import com.washingtondcsquad.tudee.presentation.features.category_detail.components.TaskStatusTabs
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoryDetailScreen(
    modifier: Modifier = Modifier,
    categoryId: CategoryID,
    onBack: () -> Unit,
    onDeleteSuccessNav: () -> Unit,
    viewModel: CategoryDetailsScreenViewModel = koinViewModel<CategoryDetailsScreenViewModel>()
) {

    val state by viewModel.state.collectAsState()
    viewModel.setCategoryId(categoryId = categoryId)
    LaunchedEffect(Unit) {
        viewModel.getCategoryDetails(categoryId)
        viewModel.getTasksByCategory(categoryId)
    }


    CategoryDetailContents(
        state = state, onDismissBottomSheet = {
        viewModel.showEditCategoryBottomSheet(false)
        viewModel.showDeleteCategoryBottomSheet(false)
    }, onSaveEditCategory = { title, imagePath ->
        viewModel.editCategory(
            title = title, imagePath = imagePath
        )
    }, onShowEdit = { viewModel.showEditCategoryBottomSheet(isShow = true) }, onShowDelete = {
        viewModel.showDeleteCategoryBottomSheet(isShow = true)
    }, onDelete = {
        viewModel.deleteCategory(
            state.categoryID, AddedByUser(state.categoryImagePath)
        )
        onDeleteSuccessNav()

    }, onBack = {
        onBack()
    }, modifier = modifier.background(AppTheme.colors.surface)
    )
}


@Composable
fun CategoryDetailContents(
    state: CategoryDetailsScreenUiState,
    onDismissBottomSheet: () -> Unit,
    onShowEdit: () -> Unit,
    onShowDelete: () -> Unit,
    onSaveEditCategory: (title: String, imagePath: String) -> Unit,
    onDelete: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val selectedTabIndex = remember { mutableIntStateOf(pagerState.currentPage) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            selectedTabIndex.intValue = it
        }
    }
    val currentTasks = when (selectedTabIndex.intValue) {
        0 -> state.inProgressTasks
        1 -> state.toDoTasks
        else -> state.doneTasks
    }

    Column(
        modifier = Modifier
            .background(
                AppTheme.colors.surfaceHigh
            )
            .windowInsetsPadding(androidx.compose.foundation.layout.WindowInsets.statusBars)

    ) {
        // top app bar
        CategoryDetailTopAppBar(
            title = state.categoryTitle,
            onBack = {
                onBack()
            },
            onEdit = {
                onShowEdit()
            },
            modifier = modifier
                .padding(horizontal = 16.dp)
                .height(64.dp),
            isCategoryPredefined = state.isCategoryPredefined
        )

        TaskStatusTabs(
            selectedTabIndex.intValue,
            inProgressCount = state.inProgressTasks.size,
            toDoCount = state.toDoTasks.size,
            doneCount = state.doneTasks.size,
            pagerState = pagerState
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(AppTheme.colors.surface)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = if (currentTasks.isEmpty()) Arrangement.Center else Arrangement.Top,
                contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
            ) {
                if (currentTasks.isEmpty()) {
                    item {
                        NoTaskInCategoryPlaceHolder(categoryName = state.categoryTitle)
                    }
                } else {
                    items(currentTasks) {
                        TaskCard(
                            taskUiState = it,
                            onTaskClicked = {},
                        )
                    }
                }

            }

        }

        EditCategoryBottomSheet(
            currentTitle = state.categoryTitle,
            currentImagePath = state.categoryImagePath,
            isDeleteMode = state.isShowingDeleteCategoryBottomSheet,
            showBottomSheet = state.isShowingEditCategoryBottomSheet,
            onDismiss = { onDismissBottomSheet() },
            onSaveCategory = { title, imagePath ->
                onSaveEditCategory(title, imagePath)

            },
            onShowDelete = {
                onShowDelete()
            },
            onDeleteCategory = {
                onDelete()
            })
        SnackbarHandler()

    }
}

@Preview()
@Composable
private fun PreviewCategoryDetailScreen() {
    //CategoryDetailScreen()
}