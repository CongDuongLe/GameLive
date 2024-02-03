package com.cdle.gamelive.presentation.screens.detail

import RatingBar
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.cdle.gamelive.data.remote.game.GameDetailDto
import com.cdle.gamelive.presentation.components.DisplayHtmlText
import com.cdle.gamelive.utils.extractTextFromHtmlString
import com.cdle.gamelive.data.remote.game.GameScreenshotDto
import com.cdle.gamelive.presentation.screens.detail.components.ImageWithLoadingState
import com.cdle.gamelive.presentation.screens.detail.components.LoadingIndicator

@SuppressLint("SuspiciousIndentation")
@Composable
fun DetailScreen(
    gameId: Int,
    navController: NavController,
    viewModel: GameDetailViewModel = hiltViewModel()
) {
    val gameDetailState by viewModel.gameDetail.collectAsState()
    val gameScreenshotState by viewModel.gameScreenshot.collectAsState()

    LaunchedEffect(key1 = gameId) {
        viewModel.getGameDetail(id = gameId)
        viewModel.getGameScreenshots(id = gameId)
    }

    when (val detailState = gameDetailState) {
        is GameDetailState.Empty -> {
            // Handle empty state for game detail
        }

        is GameDetailState.Loading -> {
            // Handle loading state for game detail
            LoadingIndicator()
        }

        is GameDetailState.Success -> {
            val gameDetailData = detailState.data
            // Access game detail data and pass it to your content composable
            gameScreenshotState?.let { ContentScreen(gameDetailData = gameDetailData, gameScreenshotState = it) }
        }

        is GameDetailState.Error -> {
            // Handle error state for game detail
            val errorMessage = detailState.error
            // Handle the error message as needed
        }

        null -> TODO()
    }
}


@Composable
fun ContentScreen(
    gameDetailData: GameDetailDto?,
    gameScreenshotState: GameScreenshotState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            if (gameDetailData != null) {
                ParallaxHeader(gameDetail = gameDetailData as GameDetailDto)
                Spacer(modifier = Modifier.height(8.dp))
            }


            when (gameScreenshotState) {
                is GameScreenshotState.Empty -> {
                    // Handle empty state for game detail
                }

                is GameScreenshotState.Loading -> {
                    // Handle loading state for game detail
                    LoadingIndicator()
                }

                is GameScreenshotState.Success -> {
                    val screenshotData = gameScreenshotState.data
                    // Access game detail data and pass it to your content composable
                    Content(gameScreenshot = screenshotData)
                }

                is GameScreenshotState.Error -> {
                    // Handle error state for game detail
                    val errorMessage = gameScreenshotState.error
                    // Handle the error message as needed
                }

                null -> TODO()
            }


//                Content(gameScreenshot = screenshotData)

        }
    }
}


@Composable
fun ParallaxHeader(gameDetail: GameDetailDto) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = gameDetail.backgroundImage,
                contentDescription = "Game image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .background(
                        // make it linear gradient
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.4f),
                                Color.Black.copy(alpha = 0.8f),
                            )
                        )
                    )
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = gameDetail.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = gameDetail.released,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = gameDetail.website ?: "",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    RatingBar(rating = gameDetail.rating.toFloat(), modifier = Modifier.height(16.dp))
                }

            }
        }
    }
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            Text(
                text = "Description",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(8.dp))
            DisplayHtmlText(htmlText = extractTextFromHtmlString(gameDetail.description))
        }
    }

}

@Composable
fun Content(
    gameScreenshot: GameScreenshotDto
) {

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Game screenshots",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 16.dp
                ),
                userScrollEnabled = true,
            ) {

                items(gameScreenshot.results.size) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        ImageWithLoadingState(
                            imageUrl = gameScreenshot.results[it].image,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent)
                        )


                    }

                }
            }
        }

    }


}


//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .statusBarsPadding()
//                .navigationBarsPadding()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.Top
//            ) {
//
//
//
//
//                ParallaxHeader(gameDetail = gameDetail)
//                Spacer(modifier = Modifier.height(8.dp))
//                Content(gameScreenshot = gameScreenshotState as GameScreenshotDto)
//            }
//
//        }
