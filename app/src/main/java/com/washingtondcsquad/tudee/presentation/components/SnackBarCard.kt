package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.AppTheme
import com.washingtondcsquad.tudee.presentation.utils.modifierExensions.dropShadow


@Composable
fun SnackBarCard(
    modifier: Modifier = Modifier,
    isError: Boolean,
    message: String
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(58.dp)
            .dropShadow(
                shape = RoundedCornerShape(16.dp),
                blur = 16.dp,
                color = Color.Black.copy(0.12f),
            )
            .clip(RoundedCornerShape(12.dp))
            .background(AppTheme.colors.surfaceHigh)
            .padding(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(if (isError) AppTheme.colors.errorVariant else AppTheme.colors.greenVariant)

        ) {
            if (isError) {
                Icon(
                    painter = painterResource(R.drawable.information_diamond),
                    contentDescription = "error icon",
                    tint = AppTheme.colors.error

                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.checkmark),
                    contentDescription = "success icon",
                    tint = AppTheme.colors.greenAccent
                )
            }
        }
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = message,
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.colors.body,
        )

    }
}