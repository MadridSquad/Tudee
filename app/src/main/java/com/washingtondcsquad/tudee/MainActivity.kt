package com.washingtondcsquad.tudee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.TudeeNavigationBar
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.navBarItemsList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                        Box(modifier = Modifier.fillMaxSize().background(Color.Green)) {
                            Text(
                                "home", fontSize = 40.sp, modifier = Modifier.padding(30.dp)
                            )
                        }
                    }
                    composable(route = "task") {
                        Box(modifier = Modifier.fillMaxSize().background(Color.Magenta)) {

                            Text(
                                "TASKS", fontSize = 40.sp, modifier = Modifier.padding(30.dp)
                            )
                        }
                    }
                    composable(route = "category") {
                        Box(modifier = Modifier.fillMaxSize().background(Color.Cyan)) {

                            Text(
                                "category", fontSize = 40.sp, modifier = Modifier.padding(30.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

