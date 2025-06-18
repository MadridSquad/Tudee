package com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.utils.modifierExensions.noRippleClick

@Composable
fun TudeeNavigationBar(
    navBarItemDataList: List<NavBarItemData>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(74.dp)
            .background(AppTheme.colors.surfaceHigh)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        navBarItemDataList.forEachIndexed { index, item ->
            val isSelected = currentDestination == item.route

            val iconColor by animateColorAsState(if (isSelected) AppTheme.colors.primary else AppTheme.colors.hint)
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        color = if (isSelected) AppTheme.colors.primaryVarient else Color.Transparent,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .noRippleClick {

                        if (!isSelected) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }, contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        painter = painterResource(id = item.selectedIcon),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp),
                        tint = iconColor
                    )
                } else {
                    Icon(
                        painter = painterResource(id = item.unSelectedIcon),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp),
                        tint = iconColor
                    )
                }

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewNavigationBar() {
    val navController = rememberNavController()

    TudeeNavigationBar(navBarItemsList, navController)
}

