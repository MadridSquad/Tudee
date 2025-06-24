package com.washingtondcsquad.tudee

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.os.LocaleListCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.TudeeNavigationBar
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.bottomNavBarRoutes
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.navBarItemsList
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.categories.CategoriesScreen
import com.washingtondcsquad.tudee.presentation.features.home.HomeScreen
import com.washingtondcsquad.tudee.presentation.features.onBoarding.OnBoardingScreen
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.TasksScreen
import com.washingtondcsquad.tudee.presentation.utils.SnackBarHandler
import org.koin.android.ext.android.inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val mainState by mainViewModel.state.collectAsState()

            updateAppLocale(mainState)
            AppTheme(
                useDarkTheme = mainState.isDarkTheme
            ) {
                val isOnboardingShown = mainState.hasOnBoardingShown
                val startDestination = if (isOnboardingShown) {
                    "home"
                } else {
                    Log.d("sdasdsad", "onCreate: ")
                    "onboarding"
                }
                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry.value?.destination?.route

                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(currentDestination in bottomNavBarRoutes) {
                            TudeeNavigationBar(
                                navBarItemDataList = navBarItemsList,
                                navController = navController,
                                modifier = Modifier.windowInsetsPadding(windowInsets)
                            )
                        }

                    }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = "onboarding") {
                            OnBoardingScreen(
                                onFinish = {
                                    navController.navigate("home") {
                                        popUpTo("onboarding") { inclusive = true }
                                    }
                                },
                            )
                        }
                        composable(route = "home") {
                            HomeScreen()
                        }
                        composable(route = "task") {
                            TasksScreen()
                        }
                        composable(route = "category") {
                            CategoriesScreen()
                        }
                    }
                }
            }
            SnackBarHandler()
        }
    }
//
//    private fun createPreDefineCategories() {
//        CoroutineScope(Dispatchers.IO).launch {
//            listOf(
//                "Education",
//                "Shopping",
//                "Medical",
//                "Gym",
//                "Entertainment",
//                "Cooking",
//                "Family & friend",
//                "Traveling",
//                "Agriculture",
//                "Coding",
//                "Adoration",
//                "Fix bug",
//                "Cleaning",
//                "Work",
//                "Budgeting",
//                "Self care",
//                "Event"
//            ).forEach { image ->
//                TudeeDataBase.getInstance(this@MainActivity).daoCategory().createCategory(
//                    Category(
//                        title = image, iconPath = "", taskCount = 0, id = 0
//                    ).toEntity()
//                )
//            }
//        }
//    }

    private fun updateAppLocale(state: MainActivityUiState) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(state.currentAppLocale.toLanguageTag())
        )
    }
}



