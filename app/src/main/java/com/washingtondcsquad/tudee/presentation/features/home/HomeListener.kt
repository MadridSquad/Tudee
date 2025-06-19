package com.washingtondcsquad.tudee.presentation.features.home

interface HomeListener {
    fun onTaskClicked(taskId: Int)
    fun onThemeSwitched(isDarkMode: Boolean)
}