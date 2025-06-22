package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.design.textStyle.defaultTextStyle
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.ImageSource

@Composable
fun TaskScreenComponents(
    header: String,
    taskTitle: String,
    onTitleChange: (String) -> Unit,
    taskDescription: String,
    onDescriptionChange: (String) -> Unit,
    taskDate: String,
    onShowDatePicker: () -> Unit,
    priorityList: List<Priority>,
    selectedPriority: Priority?,
    onPrioritySelected: (Priority) -> Unit,
    categoryList: List<Category>,
    onCategorySelected: (Category) -> Unit,
    selectedCategory: Category?,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 100.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = header,
                modifier = Modifier.padding(bottom = 8.dp),
                style = defaultTextStyle.title.large
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            AppTextField(
                prefixIconPainter = painterResource(R.drawable.add_task_title),
                hintText = stringResource(R.string.task_title_hint),
                value = taskTitle,
                onValueChange = { onTitleChange(it) },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp)

            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            AppTextField(
                prefixIconPainter = null,
                hintText = stringResource(R.string.description_hint),
                value = taskDescription,
                onValueChange = { onDescriptionChange(it) },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(168.dp)
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            AppTextField(
                prefixIconPainter = painterResource(R.drawable.add_task_calendar),
                hintText = taskDate,
                value = taskDate,
                onValueChange = { },
                onPrefixIconClick = onShowDatePicker,
                readOnly = true,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(R.string.priority),
                style = defaultTextStyle.title.medium,
                color = AppTheme.colors.title
            )
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            LazyRow(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(priorityList) { priority ->
                    TaskPriorityCard(
                        priority = priority,
                        isSelected = selectedPriority == priority,
                        onClick = { onPrioritySelected(priority) },
                    )
                }
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = stringResource(R.string.category),
                style = defaultTextStyle.title.medium,
                color = AppTheme.colors.title,
                modifier = Modifier.padding( bottom = 8.dp)
            )
        }

        items(categoryList) { category ->
            CategoryCard(
                title = "adfadf",
                imageSource = ImageSource.Drawable(id = R.drawable.gym),
                onClick = { onCategorySelected(category) },
                isSelected = selectedCategory == category,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TaskScreenComponentsPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
    ) {

        TaskScreenComponents(
            header = "header",
            taskTitle = "taskTitle",
            onTitleChange = {},
            taskDescription = "taskDescription",
            onDescriptionChange = {},
            taskDate = "taskDate",
            onShowDatePicker = {},
            priorityList = listOf(
                Priority.HIGH,
                Priority.MEDIUM,
                Priority.LOW
            ),
            selectedPriority = Priority.HIGH,
            onPrioritySelected = {},
            categoryList = listOf(
                Category(
                    id = 1,
                    title = "bla bla",
                    iconPath = R.drawable.gym.toString(),
                    taskCount = 44,
                    isPredefined = true
                ),
                Category(
                    id = 1,
                    title = "bla bla",
                    iconPath = R.drawable.gym.toString(),
                    taskCount = 44,
                    isPredefined = true
                )
            ),
            onCategorySelected = {},
            selectedCategory = Category
                (
                id = 1,
                title = "bla bla",
                iconPath = R.drawable.gym.toString(),
                taskCount = 44,
                isPredefined = true
            )

        )
    }


}