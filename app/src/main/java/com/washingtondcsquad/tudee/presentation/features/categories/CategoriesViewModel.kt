package com.washingtondcsquad.tudee.presentation.features.categories

import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.categories.mapper.toUi
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoriesService: CategoriesService,
) : BaseViewModel<CategoriesScreenStatus>(CategoriesScreenStatus()), CategoriesEvent {


    init {
        getAllCategories()
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            categoriesService.getAllCategories().collect {
                updateState {
                    copy(categories = it.map { it.toUi() })
                }
            }
        }
    }

    override fun onCategoryClick(category: CategoriesScreenStatus.Category) {
        tryToExecute(
            request = {
                categoriesService.getCategoryById(CategoryID(category.id))
            },
            onSuccess = {
                //todo navigate to category details
            },
            onError = {
                //todo show error
            }
        )

    }

    override fun addCategoryClick(
        title: String,
        categoryIconPath: String,
    ) { //TODO handle this function
        viewModelScope.launch {
            categoriesService.createCategory(title,categoryIconPath)
        }
    }

}