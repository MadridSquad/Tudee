package com.washingtondcsquad.tudee.presentation.features.category_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailTopAppBar(
    isCategoryPredefined: Boolean,
    title: String,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.surfaceHigh)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)

                .background(
                    shape = CircleShape, color = AppTheme.colors.surfaceHigh
                )
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = AppTheme.colors.stroke,
                    shape = CircleShape
                )
                .clickable {
                    onBack()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.back_arrow_icon),
                contentDescription = null,
                tint = AppTheme.colors.body,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.textStyle.title.large,
            color = AppTheme.colors.title,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(
                    start = 12.dp,
                    top = 20.dp,
                    bottom = 20.dp
                )
        )
        if (!isCategoryPredefined) {
            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(40.dp)

                    .background(
                        shape = CircleShape, color = AppTheme.colors.surfaceHigh
                    )
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = AppTheme.colors.stroke,
                        shape = CircleShape
                    )
                    .clickable {
                        onEdit()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.edit_icon),
                    tint = AppTheme.colors.body,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }


    }
}

@Preview
@Composable
private fun PreviewTopBar() {
    CategoryDetailTopAppBar(
        title = "Coding",
        onBack = {},
        onEdit = {},
        isCategoryPredefined = false,
        //modifier = Modifier.height(60.dp)
    )
}