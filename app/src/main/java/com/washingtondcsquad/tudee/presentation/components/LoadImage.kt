package com.washingtondcsquad.tudee.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.washingtondcsquad.tudee.domain.entity.ImageCategory

@Composable
fun LoadImage(imageSource: ImageCategory, modifier: Modifier = Modifier) {

    when (imageSource) {
        is ImageCategory.AddedByUser -> {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageSource.value)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
        }

        is ImageCategory.PredefinedDrawable -> {
            Image(
                painter = painterResource(id = imageSource.id),
                contentDescription = "",
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
        }
    }
}