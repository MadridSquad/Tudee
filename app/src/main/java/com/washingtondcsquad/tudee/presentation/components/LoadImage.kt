package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun LoadImage(imageSource: ImageSource, modifier: Modifier = Modifier) {

    when (imageSource) {
        is ImageSource.AddedByUser -> {
            AsyncImage(

                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageSource.value)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .background(
                        color = AppTheme.colors.surfaceHigh,
                        shape = CircleShape
                    )
                    .padding(18.dp)
                    .size(44.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

        }

        is ImageSource.PredefinedDrawable -> {
            Image(
                painter = painterResource(id = imageSource.id),
                contentDescription = "",
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
        }
    }
}