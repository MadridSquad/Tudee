package com.washingtondcsquad.tudee.presentation.features.category_detail

import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.domain.entity.ImageSource.AddedByUser
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarController
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarEvent
import com.washingtondcsquad.tudee.presentation.features.home.toTaskUiState
import kotlinx.coroutines.launch

class CategoryDetailsScreenViewModel(val categoryService: CategoriesService) :
    BaseViewModel<CategoryDetailsScreenUiState>(
        CategoryDetailsScreenUiState()
    ) {


    fun getCategoryDetails(categoryId: CategoryID) {
        tryToExecute<Category>(
            request = {
                categoryService.getCategoryById(categoryId = categoryId)
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
            onError = {
                viewModelScope.launch {
                    SnackbarController.sendEvent(SnackbarEvent(it.message.toString()))
                }
            }
        )
    }

    fun getTasksByCategory(categoryID: CategoryID) {

        tryToExecute<List<Task>>(
            request = {

                categoryService.getTasksByCategoryID(categoryId = categoryID)

            },
            onSuccess = {
                val inProgresTasks = it.filter { it.status == TaskStatus.IN_PROGRESS }
                val doneTasks = it.filter { it.status == TaskStatus.DONE }
                val toDoTasks = it.filter { it.status == TaskStatus.TODO }
                updateState {
                    copy(
                        inProgressTasks = inProgresTasks.map { it.toTaskUiState() },
                        toDoTasks = toDoTasks.map { it.toTaskUiState() },
                        doneTasks = doneTasks.map { it.toTaskUiState() }
                    )
                }
            },
            onError = {
                viewModelScope.launch {
                    SnackbarController.sendEvent(SnackbarEvent(it.message.toString()))
                }
            }
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
            request = {
                categoryService.editCategory(
                    Category(
                        id = state.value.categoryID,
                        title = title,
                        iconPath = AddedByUser(imagePath),
                        isPredefined = false
                    )
                )
            },
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




fun deleteCategory(categoryID: CategoryID, categoryImage: ImageSource) {
    tryToExecute(
        request = {
            categoryService.deleteCategory(categoryID)
            categoryService.deleteCategoryImage(categoryImage)
        },
        onSuccess = {
            updateState {
                copy(
                    isShowingEditCategoryBottomSheet = false
                )
            }

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


