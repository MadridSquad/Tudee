package com.washingtondcsquad.tudee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.TudeeNavigationBar
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.navBarItemsList
import com.washingtondcsquad.tudee.presentation.design.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            AppTheme(
                useDarkTheme = false
            ) {

                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        TudeeNavigationBar(
                            navBarItemDataList = navBarItemsList,
                            navController = navController,
                            modifier = Modifier.windowInsetsPadding(windowInsets)
                        )
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
            }
        }
    }
}


