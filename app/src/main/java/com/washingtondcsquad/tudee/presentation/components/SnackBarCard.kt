package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme


@Composable
private fun SnackBarCard(
    modifier: Modifier = Modifier,
    isError: Boolean,
    message: String
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 328.dp, height = 58.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color(0x1F000000),
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
            text = message,
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.colors.body
        )

    }
}