package com.washingtondcsquad.tudee.presentation.features.tasks_screen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun NoTasks() {
    Row {
        Box(
            modifier = Modifier
                .zIndex(1f)
                .offset(y = (-25).dp, x = (15).dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 2.dp
                    )
                )
                .background(AppTheme.colors.surfaceHigh)
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    stringResource(R.string.tasks_screen_no_tasks_title),
                    style = AppTheme.textStyle.title.small,
                    color = AppTheme.colors.body
                )
                Text(
                    stringResource(R.string.tasks_screen_no_tasks_desc),
                    style = AppTheme.textStyle.body.small,
                    color = AppTheme.colors.hint
                )
            }

        }

        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = painterResource(R.drawable.no_tasks_image_container),
                contentDescription = ""
            )
            Image(
                painter = painterResource(R.drawable.no_tasks_image_overlay),
                contentDescription = ""
            )
            Image(
                painter = painterResource(R.drawable.no_tasks_tudee),
                contentDescription = "", modifier = Modifier
                    .height(100.dp)
                    .width(107.dp)
            )
            Image(
                modifier = Modifier
                    .zIndex(1f)
                    .offset(y = (-32).dp, x = (-97).dp),
                painter = painterResource(R.drawable.no_tasks_progress_indicator),
                contentDescription = ""
            )
        }
    }
}