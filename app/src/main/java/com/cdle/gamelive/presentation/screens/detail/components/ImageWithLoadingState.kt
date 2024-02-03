package com.cdle.gamelive.presentation.screens.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cdle.gamelive.R

@Composable
fun ImageWithLoadingState(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    // Create an ImageRequest with the provided imageUrl
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .build()
    // Use Box to overlay the loading indicator on top of the image
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // AsyncImage composable from Coil library
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(id = R.drawable.ic_microsoft),
            contentScale = ContentScale.Crop
        )
    }
}

