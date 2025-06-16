package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R

@Composable
fun StarsOverlay() {
    val stars = listOf(
        StarData(x = 24.dp, y = 5.dp, size = 7.dp),
        StarData(x = 18.dp, y = 6.dp, size = 3.dp),
        StarData(x = 10.dp, y = 8.dp, size = 5.dp),
        StarData(x = 7.dp, y = 14.dp, size = 3.dp),
        StarData(x = 14.dp, y = 20.dp, size = 3.dp),
        StarData(x = 7.dp, y = 24.dp, size = 3.dp),
        StarData(x = 22.dp, y = 26.dp, size = 4.dp),
        StarData(x = 20.dp, y = 16.dp, size = 5.dp)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        stars.forEachIndexed { index, star ->
            Image(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "Star $index",
                modifier = Modifier
                    .size(star.size)
                    .offset(x = star.x, y = star.y)
            )
        }
    }
}

data class StarData(val x: Dp, val y: Dp, val size: Dp)
