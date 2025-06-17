package com.washingtondcsquad.tudee.presentation.components.bottom_nav_bar

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme

@Composable
fun TudeeNavigationBar(modifier: Modifier = Modifier) {

    var selectedPage by remember { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = AppTheme.colors.surfaceHigh
    ) {
        navBarItemsList.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedPage, onClick = {
                selectedPage = index
                // navController.navigate()
            }, icon = {
                if (index == selectedPage) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(
                                color = AppTheme.colors.primaryVarient,
                                shape = RoundedCornerShape(16.dp)
                            ), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.selectedIcon),
                            contentDescription = item.label,
                            modifier = Modifier.size(24.dp)
                        )

                    }
                } else {
                    Icon(
                        painter = painterResource(id = item.unSelectedIcon),
                        contentDescription = item.label
                    )
                }
            }, colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AppTheme.colors.primary,
                unselectedIconColor = AppTheme.colors.hint,
                indicatorColor = Color.Transparent
            ), alwaysShowLabel = false, modifier = Modifier

            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewNavigationBar() {
    TudeeNavigationBar()
}