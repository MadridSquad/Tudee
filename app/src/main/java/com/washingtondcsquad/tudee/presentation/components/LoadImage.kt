package com.washingtondcsquad.tudee.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.ImageSource

@Composable
fun LoadImage(imageSource: ImageSource, modifier: Modifier = Modifier) {

    when (imageSource) {
        is ImageSource.AddedByUser -> {

            Image(
                painter = painterResource(R.drawable.education_icon),
                contentDescription = "",
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
//            AsyncImage(
//
//                        model = ImageRequest.Builder(LocalContext.current)
//                    .data(imageSource.value)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = "",
//                modifier = modifier,
//                contentScale = ContentScale.Crop
//            )
//            Log.d("LoadImage", "Image path: ${imageSource.value}")

        }

        is ImageSource.PredefinedDrawable -> {
//            R.drawable
//            Log.e("MY_TAG",imageSource.id.toString())
//            painter = painterResource(id = imageSource.id)
            Image(
                painter = painterResource(id =R.drawable.entertainment),
                contentDescription = "",
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
        }
    }
}