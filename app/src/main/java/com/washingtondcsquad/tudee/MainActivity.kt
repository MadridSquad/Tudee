package com.washingtondcsquad.tudee

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.washingtondcsquad.tudee.domain.services.AppPreferencesService
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.TudeeNavigationBar
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.bottomNavBarRoutes
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.navBarItemsList
import com.washingtondcsquad.tudee.presentation.design.AppTheme
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
            val isOnboardingShown by appPreferencesService.hasOnboardingBeenShown()
                .collectAsState(initial = true)
            AppTheme(
                useDarkTheme = isDarkMode
            ) {

                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry.value?.destination?.route
                val startDestination = if (isOnboardingShown) {
                    "home"
                } else {
                    "onboarding"
                }
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
                            // Category screen
                        }
                    }
                }

            }
        }
    }
}


