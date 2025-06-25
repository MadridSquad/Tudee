package com.washingtondcsquad.tudee.presentation.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.SnackBarCard
import com.washingtondcsquad.tudee.presentation.components.snack_bar.ObserveAsEvent
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarController
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import kotlinx.coroutines.launch

@Composable
fun SnackBarHandler() {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    ObserveAsEvent(
        flow = SnackbarController.event,
        onEvent = { event ->
            coroutineScope.launch {
                snackBarHostState.currentSnackbarData?.dismiss()
                snackBarHostState.showSnackbar(
                    message = event.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    )

    SnackbarHost(
        hostState = snackBarHostState,
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
