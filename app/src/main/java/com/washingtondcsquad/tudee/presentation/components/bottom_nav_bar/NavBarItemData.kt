package com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar

import androidx.annotation.DrawableRes
import com.washingtondcsquad.tudee.R

class NavBarItemData(
    val route: String,
    val label: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unSelectedIcon: Int
)

var navBarItemsList = listOf(
    NavBarItemData(
        route = "home",
        label = "Home",
        selectedIcon = R.drawable.home_icon_filled,
        unSelectedIcon = R.drawable.home_icon_outlined,
    ), NavBarItemData(
        route = "task",
        label = "Task",
        selectedIcon = R.drawable.task_icon_filled,
        unSelectedIcon = R.drawable.task_icon_outlined,
    ), NavBarItemData(
        route = "category",
        label = "Category",
        selectedIcon = R.drawable.category_icon_filled,
        unSelectedIcon = R.drawable.category_icon_outlined,
    )
)