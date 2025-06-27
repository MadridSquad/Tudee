package com.washingtondcsquad.tudee.presentation.features.category_detail

import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.domain.entity.ImageSource.AddedByUser
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarController
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarEvent
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                        categoryID = it.id,
                        isCategoryPredefined = it.isPredefined,
                        categoryTitle = it.title,
                        categoryImagePath = if(it.iconPath is ImageSource.AddedByUser) (it.iconPath as AddedByUser).value else ""
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
        viewModelScope.launch {
           categoryService.getAllCategories().collect {categoryList->
                val tasks = categoryService.getTasksByCategoryID(categoryId = categoryID)
                val inProgresTasks = tasks.filter { it.status == TaskStatus.IN_PROGRESS }
                val doneTasks = tasks.filter { it.status == TaskStatus.DONE }
                val toDoTasks = tasks.filter { it.status == TaskStatus.TODO }
               withContext (Dispatchers.Main){
                   updateState {
                       copy(
                           inProgressTasks = inProgresTasks.map {
                               TaskUiState(
                                   taskId = it.id,
                                   taskDate = it.date.toString(),
                                   taskTitle = it.title,
                                   taskDescription = it.description,
                                   taskPriority = it.priority,
                                   taskStatus = it.status.toString(),
                                   categoryImage = categoryList.find { it.id == categoryID }?.iconPath ?: ImageSource.PredefinedDrawable(R.drawable.gym)
                               )
                           },
                           toDoTasks = toDoTasks.map {
                               TaskUiState(
                                   taskId = it.id,
                                   taskDate = it.date.toString(),
                                   taskTitle = it.title,
                                   taskDescription = it.description,
                                   taskPriority = it.priority,
                                   taskStatus = it.status.toString(),
                                   categoryImage = categoryList.find { it.id == categoryID }?.iconPath ?: ImageSource.PredefinedDrawable(R.drawable.gym)
                               )
                           },
                           doneTasks = doneTasks.map {
                               TaskUiState(
                                   taskId = it.id,
                                   taskDate = it.date.toString(),
                                   taskTitle = it.title,
                                   taskDescription = it.description,
                                   taskPriority = it.priority,
                                   taskStatus = it.status.toString(),
                                   categoryImage = categoryList.find { it.id == categoryID }?.iconPath ?: ImageSource.PredefinedDrawable(R.drawable.gym)
                               )
                           }
                       )
                   }
               }
            }
        }
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
                val imageUrl = categoryService.saveCategoryImage(iconPath = imagePath)
                categoryService.editCategory(
                    Category(
                        id = state.value.categoryID,
                        title = title,
                        iconPath = AddedByUser(imageUrl),
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
            onError = {
                println("delete category:  ${it.message}")
            }
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


