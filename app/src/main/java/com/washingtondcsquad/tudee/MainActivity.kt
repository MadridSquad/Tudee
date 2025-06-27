package com.washingtondcsquad.tudee

import SplashScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.services.AppPreferencesService
import com.washingtondcsquad.tudee.presentation.components.SnackBarCard
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.TudeeNavigationBar
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.bottomNavBarRoutes
import com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar.navBarItemsList
import com.washingtondcsquad.tudee.presentation.components.snack_bar.ObserveAsEvent
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarController
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.features.categories.CategoriesScreen
import com.washingtondcsquad.tudee.presentation.features.category_detail.CategoryDetailScreen
import com.washingtondcsquad.tudee.presentation.features.home.HomeScreen
import com.washingtondcsquad.tudee.presentation.features.onBoarding.OnBoardingScreen
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.TasksScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

val LocalInnerPaddingProvider =
    staticCompositionLocalOf<PaddingValues> { PaddingValues() }

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

                    }

                    else -> {
                        val onboarding = if (isOnboardingShown) {
                            "home"
                        } else {
                            "onboarding"
                        }
                        val navController = rememberNavController()
                        val navBackStackEntry = navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry.value?.destination?.route


                        Scaffold(
                            bottomBar = {
                                if (currentDestination in bottomNavBarRoutes) {
                                    TudeeNavigationBar(
                                        navBarItemDataList = navBarItemsList,
                                        navController = navController,
                                        modifier = Modifier.windowInsetsPadding(windowInsets)
                                    )
                                }

                            }) { innerPadding ->
                            CompositionLocalProvider(
                                LocalInnerPaddingProvider provides innerPadding
                            ) {
                                NavHost(
                                    navController = navController,
                                    startDestination = "splash",
                                ) {
                                    composable(route = "splash") {
                                        SplashScreen(
                                            title = stringResource(R.string.app_name),
                                            isDarkTheme =isDarkMode ,
                                            onFinish = {
                                                navController.navigate(onboarding) {
                                                    popUpTo("splash") { inclusive = true }
                                                }
                                            },
                                        )
                                    }
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
                                        CategoriesScreen(onCategoryClick = { categoryId ->
                                            navController.navigate("category/${categoryId.categoryId}")
                                        })
                                    }
                                    composable(
                                        route = "category/{categoryId}",
                                        arguments = listOf(
                                            navArgument("categoryId") {
                                                type = NavType.LongType
                                            }
                                        )
                                    ) { backStackEntry ->
                                        val categoryId =
                                            backStackEntry.arguments?.getLong("categoryId")

                                        CategoryDetailScreen(
                                            categoryId = CategoryID(
                                                categoryId ?: throw Exception("null category id")
                                            ),
                                            onBack = {
                                                navController.popBackStack()
                                            },
                                            onDeleteSuccessNav = {
                                                navController.navigate("category") {
                                                    popUpTo("category")
                                                }
                                            },
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
            SnackbarHandler()
        }

    }
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

