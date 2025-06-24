package com.washingtondcsquad.tudee.presentation.features.category_detail

import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarController
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarEvent
import com.washingtondcsquad.tudee.presentation.features.home.toTaskUiState
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.ImageSource
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class CategoryDetailsScreenViewModel(val categoryService: CategoriesService) :
    BaseViewModel<CategoryDetailsScreenUiState>(
        CategoryDetailsScreenUiState()
    ) {


    fun getCategoryDetails(categoryId: CategoryID) {
        tryToExecute<Category>(
            request = {
                //categoryService.getCategory(categoryId)
                Category(
                    id = CategoryID(1L),
                    title = "Reading",
                    iconPath = ImageSource.Path("path/to/icon"),
                    taskCount = 5,
                    isPredefined = false
                )
            },
            onSuccess = {
                updateState {
                    copy(
                        isCategoryPredefined = it.isPredefined,
                        categoryTitle = it.title,
                        categoryImagePath = it.iconPath.toString()
                    )
                }
            },
            onError = {}
        )
    }

    fun getTasksByCategory(categoryID: CategoryID) {

        tryToExecute<List<Task>>(
            request = {

                dummyTasks

            },
            onSuccess = {
                val inProgresTasks = dummyTasks.filter { it.status == TaskStatus.IN_PROGRESS }
                val doneTasks = dummyTasks.filter { it.status == TaskStatus.DONE }
                val toDoTasks = dummyTasks.filter { it.status == TaskStatus.TODO }
                updateState {
                    copy(
                        inProgressTasks = inProgresTasks.map { it.toTaskUiState() },
                        toDoTasks = toDoTasks.map { it.toTaskUiState() },
                        doneTasks = doneTasks.map { it.toTaskUiState() }
                    )
                }
            },
            onError = {}
        )
    }

    fun showEditCategoryBottomSheet(isShow: Boolean) {
        updateState {
            copy(
                isShowingEditCategoryBottomSheet = isShow
            )
        }
    }

    fun showDeleteCategoryBottomSheet(isShow: Boolean) {
        updateState {
            copy(
                isShowingDeleteCategoryBottomSheet = isShow
            )
        }
    }

    fun editCategory(title: String, imagePath: String) {
        tryToExecute(
            request = {},
            onSuccess = {
                updateState {
                    copy(
                        isShowingEditCategoryBottomSheet = false,
                        categoryTitle = title
                    )
                }
                viewModelScope.launch {
                    // show snack bar
                    SnackbarController.sendEvent(event = SnackbarEvent("Edited category successfully."))
                }
            },
            onError = {
                viewModelScope.launch {
                    // show snack bar
                    SnackbarController.sendEvent(event = SnackbarEvent("Edited category Error."))
                }
            }
        )
    }




fun deleteCategory(categoryID: CategoryID, onDeleteSuccessNav: () -> Unit) {
    tryToExecute(
        request = {},
        onSuccess = {
            updateState {
                copy(
                    isShowingEditCategoryBottomSheet = false
                )
            }
            onDeleteSuccessNav()

        },
        onError = {}
    )
}

fun setCategoryId(categoryId: CategoryID) {
    updateState {
        copy(
            categoryID = categoryId
        )
    }
}

}


val dummyTasks = listOf(
    Task(
        id = TaskID(1L),
        categoryId = 1L,
        title = "Buy groceries",
        description = "Milk, Bread, Eggs, and Fruits",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.TODO,
        priority = Priority.MEDIUM
    ),
    Task(
        id = TaskID(2L),
        categoryId = 1L,
        title = "Workout",
        description = "45-minute gym session",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.IN_PROGRESS,
        priority = Priority.HIGH
    ),
    Task(
        id = TaskID(3L),
        categoryId = 1L,
        title = "Read a book",
        description = "Finish reading chapter 4 of 'Atomic Habits'",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.DONE,
        priority = Priority.LOW
    ),
    Task(
        id = TaskID(1L),
        categoryId = 1L,
        title = "Buy groceries",
        description = "Milk, Bread, Eggs, and Fruits",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.TODO,
        priority = Priority.MEDIUM
    ),
    Task(
        id = TaskID(2L),
        categoryId = 2L,
        title = "Workout",
        description = "45-minute gym session",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.IN_PROGRESS,
        priority = Priority.HIGH
    ),
    Task(
        id = TaskID(3L),
        categoryId = 3L,
        title = "Read a book",
        description = "Finish reading chapter 4 of 'Atomic Habits'",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.DONE,
        priority = Priority.LOW
    ),
    Task(
        id = TaskID(1L),
        categoryId = 1L,
        title = "Buy groceries",
        description = "Milk, Bread, Eggs, and Fruits",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.TODO,
        priority = Priority.MEDIUM
    ),
    Task(
        id = TaskID(2L),
        categoryId = 1L,
        title = "Workout",
        description = "45-minute gym session",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.IN_PROGRESS,
        priority = Priority.HIGH
    ),
    Task(
        id = TaskID(3L),
        categoryId = 1L,
        title = "Read a book",
        description = "Finish reading chapter 4 of 'Atomic Habits'",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.DONE,
        priority = Priority.LOW
    ),
    Task(
        id = TaskID(1L),
        categoryId = 1L,
        title = "Buy groceries",
        description = "Milk, Bread, Eggs, and Fruits",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.TODO,
        priority = Priority.MEDIUM
    ),
    Task(
        id = TaskID(2L),
        categoryId = 1L,
        title = "Workout",
        description = "45-minute gym session",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.IN_PROGRESS,
        priority = Priority.HIGH
    ),
    Task(
        id = TaskID(3L),
        categoryId = 3L,
        title = "Read a book",
        description = "Finish reading chapter 4 of 'Atomic Habits'",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.DONE,
        priority = Priority.LOW
    ),
    Task(
        id = TaskID(1L),
        categoryId = 2L,
        title = "Buy groceries",
        description = "Milk, Bread, Eggs, and Fruits",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.TODO,
        priority = Priority.MEDIUM
    ),
    Task(
        id = TaskID(2L),
        categoryId = 3L,
        title = "Workout",
        description = "45-minute gym session",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.IN_PROGRESS,
        priority = Priority.HIGH
    ),
    Task(
        id = TaskID(3L),
        categoryId = 2L,
        title = "Read a book",
        description = "Finish reading chapter 4 of 'Atomic Habits'",
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        status = TaskStatus.DONE,
        priority = Priority.LOW
    )

)
