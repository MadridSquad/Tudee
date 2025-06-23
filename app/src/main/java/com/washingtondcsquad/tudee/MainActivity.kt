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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.presentation.components.SnackBarCard
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.TudeeNavigationBar
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.bottomNavBarRoutes
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.navBarItemsList
import com.washingtondcsquad.tudee.presentation.components.snack_bar.ObserveAsEvent
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarController
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.categories.CategoriesScreen
import com.washingtondcsquad.tudee.presentation.features.home.HomeScreen
import com.washingtondcsquad.tudee.presentation.features.onBoarding.OnBoardingScreen
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.TasksScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val mainState by mainViewModel.state.collectAsState()

            AppTheme(
                useDarkTheme = mainState.isDarkTheme
            ) {
                when (val isOnboardingShown = mainState.hasOnBoardingShown) {
                    null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    else -> {
                        val startDestination = if (isOnboardingShown) {
                            "home"
                        } else {
                            Log.d("sdasdsad", "onCreate: ")
                         ///   createPreDefineCategories()
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
                }
            }
            SnackbarHandler()
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
}


@Composable
fun SnackbarHandler() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    ObserveAsEvent(
        flow = SnackbarController.event,
        onEvent = { event ->
            coroutineScope.launch {
                snackbarHostState.currentSnackbarData?.dismiss()
                snackbarHostState.showSnackbar(
                    message = event.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    )

    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { snackbarData ->
            val (iconRes, iconTint) = when {
                snackbarData.visuals.message.contains("success", ignoreCase = true) ->
                    Pair(R.drawable.checkmark, AppTheme.colors.greenAccent)

                snackbarData.visuals.message.contains("error", ignoreCase = true) ->
                    Pair(R.drawable.checkmark, AppTheme.colors.error)

                else ->
                    Pair(R.drawable.information_diamond, AppTheme.colors.error)
            }

            SnackBarCard(
                message = snackbarData.visuals.message,
                icon = painterResource(id = iconRes),
                iconTint = iconTint,
                iconBackgroundColor = AppTheme.colors.surface,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 48.dp)
            )

        }
    )
}

