package com.washingtondcsquad.tudee.presentation.features.category_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun NoTaskInCategoryPlaceHolder(categoryName: String,modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp, bottomStart = 16.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .shadow(
                    elevation = 4.dp, shape = shape
                )
                .background(
                    color = AppTheme.colors.surfaceHigh, shape = shape
                )
                .padding(vertical = 8.dp, horizontal = 12.dp),

            ) {
            Text(
                text = "No tasks in ${categoryName} category!",
                color = AppTheme.colors.body,
                style = AppTheme.textStyle.title.small,
            )
        }
        Image(
            painter = painterResource(id = R.drawable.empty_tasks_palceholder_background),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
        Image(
            painter = painterResource(id = R.drawable.empty_tasks_palce_holder_image),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(107.dp)
        )

    }

}
