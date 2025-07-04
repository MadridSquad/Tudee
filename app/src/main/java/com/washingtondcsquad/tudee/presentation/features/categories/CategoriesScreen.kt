package com.washingtondcsquad.tudee.presentation.features.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.LocalInnerPaddingProvider
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.presentation.components.CategoryCard
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.utils.modifierExensions.noRippleClick
import org.koin.androidx.compose.koinViewModel


@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    onCategoryClick: (categoryId: CategoryID) -> Unit
) {
    val viewModel: CategoriesViewModel = koinViewModel()
    val categoriesScreenStatus by viewModel.state.collectAsState()


    CategoriesContent(
        modifier = modifier,
        categoriesEvent = viewModel,
        categoriesScreenStatus = categoriesScreenStatus,
        onCategoryClick = onCategoryClick,

        )
}

@Composable
fun CategoriesContent(
    modifier: Modifier = Modifier,
    categoriesScreenStatus: CategoriesScreenStatus,
    categoriesEvent: CategoriesEvent,
    onCategoryClick: (categoryId: CategoryID) -> Unit
) {

    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(AppTheme.colors.surfaceHigh)
            .fillMaxSize()
            .padding(LocalInnerPaddingProvider.current)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Text(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp),
                text = stringResource(R.string.categories),
                color = AppTheme.colors.title,
                style = AppTheme.textStyle.title.large
            )

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = AppTheme.colors.surface
                    )
                    .padding(horizontal = 16.dp),
                columns = GridCells.Adaptive(minSize = 100.dp),
                contentPadding = PaddingValues(top = 12.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(categoriesScreenStatus.categories) { category ->

                    CategoryCard(
                        title = if (category.isPredefined) stringResource(category.title.toInt()) else category.title,
                        //title = category.title,
                        imageSource = category.iconPath,
                        onClick = {
                            onCategoryClick(category.id)
                        },
                        isSelected = false,
                        tasksCount = category.taskCount
                    )
                }
            }

        }

        FabIcon(
            modifier = Modifier
                .noRippleClick {
                    showBottomSheet = true
                }
                .align(Alignment.BottomEnd)
        )

        CategoryBottomSheetScreen(
            bottomSheetTitle = stringResource(R.string.add_new_category),
            bottomSheetSubTitle = stringResource(R.string.category_image),
            actionText = stringResource(R.string.add),
            showBottomSheet = showBottomSheet,
            onDismiss = { showBottomSheet = false },
            onSaveCategory = { title, categoryIconPath ->
                categoriesEvent.addCategoryClick(
                    title, categoryIconPath
                )
            })

    }


}

@Composable
private fun FabIcon(
    modifier: Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.add_category),
        contentDescription = stringResource(R.string.add_new_category),
        tint = AppTheme.colors.onPrimary,
        modifier = modifier
            .padding(end = 16.dp, bottom = 16.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                clip = false,
            )
            .background(
                brush = Brush.linearGradient(AppTheme.colors.primaryGradient), shape = CircleShape
            )
            .padding(18.dp)
            .size(28.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun CategoriesPreview(modifier: Modifier = Modifier) {
//    AppTheme(true) {
//        CategoriesContent(
//            modifier = modifier, categoriesScreenStatus = CategoriesScreenStatus(
//                listOf(
//                    CategoriesScreenStatus.Category(
//                        id = 1L, title = "Education", iconPath = "", tasksCount = 0
//                    ),
//                    CategoriesScreenStatus.Category(
//                        id = 2L, title = "Shopping", iconPath = "", tasksCount = 0
//                    ),
//                    CategoriesScreenStatus.Category(
//                        id = 3L, title = "Medical", iconPath = "", tasksCount = 0
//                    ),
//                    CategoriesScreenStatus.Category(
//                        id = 4L, title = "Gym", iconPath = "", tasksCount = 0
//                    ),
//
//
//                    )
//            ), categoriesEvent = object : CategoriesEvent {
//                override fun onCategoryClick(category: CategoriesScreenStatus.Category) {
//                }
//
//                override fun addCategoryClick(
//                    title: kotlin.String, categoryIconPath: kotlin.String
//                ) {
//
//                }
//
//
//            },
//            onCategoryClick = {}
//        )
//    }
}