package com.washingtondcsquad.tudee.presentation.features.categories

import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoriesService: CategoriesService,
    private val taskService: TasksService,
) : BaseViewModel<CategoriesScreenStatus>(CategoriesScreenStatus()), CategoriesEvent {


    init {
        getAllCategories()
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            categoriesService.getAllCategories()
                .combine(taskService.getAllTasks()) { categories, tasks ->
                    categories to tasks
                }.collect {
                    val categories = it.first
                    val tasks = it.second
                    updateState {
                        copy(
                            categories = categories.map {
                                CategoryUiState(
                                    id = it.id,
                                    title = it.title,
                                    iconPath = it.iconPath,
                                    isPredefined = it.isPredefined,
                                    taskCount = tasks.filter { task -> task.categoryId == it.id }.size
                                )
                            },
                        )
                    }
                }
        }
    }

//        viewModelScope.launch {
//            categoriesService.getAllCategories().collect {
//                updateState {
//                    copy(categories = it)
//                }
//            }
//        }
//    }

    override fun onCategoryClick(category: Category) {
        tryToExecute(
            request = {
                categoriesService.getCategoryById(category.id)
            },
            onSuccess = {
                //todo navigate to category details
            },
            onError = {
                //todo show error
            }
        )
    }

    override fun addCategoryClick(title: String, categoryIconPath: String) {
        viewModelScope.launch {
            categoriesService.createCategory(title, categoryIconPath)
        }
    }
}

