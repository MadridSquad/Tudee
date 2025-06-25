package com.washingtondcsquad.tudee.presentation.features.categories

import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
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
                    copy(categories = it)
                }
            }
        }
    }

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

