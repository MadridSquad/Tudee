package com.washingtondcsquad.tudee

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.AppPreferencesService
import com.washingtondcsquad.tudee.presentation.categories.CategoriesScreen
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.TudeeNavigationBar
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.bottomNavBarRoutes
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.navBarItemsList
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.home.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.washingtondcsquad.tudee.presentation.features.home.HomeScreen
import com.washingtondcsquad.tudee.presentation.screen.onBoarding.OnBoardingScreen
import org.koin.android.ext.android.inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    private val appPreferencesService: AppPreferencesService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val isDarkMode by appPreferencesService.isDarkModeEnabled()
                .collectAsState(initial = false)
            val isOnboardingShownFlow = appPreferencesService.hasOnboardingBeenShown()
            val isOnboardingShownState by isOnboardingShownFlow.collectAsState(initial = null)

            AppTheme(
                useDarkTheme = isDarkMode
            ) {
                when (val isOnboardingShown = isOnboardingShownState) {
                    null -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    else -> {
                        val startDestination = if (isOnboardingShown) {
                            "home"
                        } else {
                            Log.d("sdasdsad", "onCreate: ")
                            createPreDefineCategories()
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
                                    // tasks screen
                                }
                                composable(route = "category") {
                                    CategoriesScreen()
                                }
                            }
                        }
                    }
                }
            }


            }
        }

    private fun createPreDefineCategories() {
        CoroutineScope(Dispatchers.IO).launch {
            listOf(
                "Education",
                "Shopping",
                "Medical",
                "Gym",
                "Entertainment",
                "Cooking",
                "Family & friend",
                "Traveling",
                "Agriculture",
                "Coding",
                "Adoration",
                "Fix bug",
                "Cleaning",
                "Work",
                "Budgeting",
                "Self care",
                "Event"
            ).forEach { image ->
                TudeeDataBase.getInstance(this@MainActivity).daoCategory().createCategory(
                    Category(
                        title = image,
                        iconPath = "",
                        taskCount = 0,
                        id = 0
                    ).toEntity()
                )
            }
        }
    }
}




