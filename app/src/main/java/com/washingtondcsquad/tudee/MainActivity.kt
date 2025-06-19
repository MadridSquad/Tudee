package com.washingtondcsquad.tudee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
<<<<<<< HEAD
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.TudeeNavigationBar
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.bottomNavBarRoutes
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.navBarItemsList
=======
>>>>>>> origin/feature/#20-home-screan
import com.washingtondcsquad.tudee.presentation.design.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            AppTheme(
                useDarkTheme = false
            ) {

<<<<<<< HEAD
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
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = "home") {
                            // home screen
                        }
                        composable(route = "task") {
                            // tasks screen
                        }
                        composable(route = "category") {
                            // Category screen
                        }
                    }
                }
=======

>>>>>>> origin/feature/#20-home-screan
            }
        }
    }
}


