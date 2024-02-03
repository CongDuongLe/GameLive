package com.cdle.gamelive.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.cdle.gamelive.domain.Game
import com.cdle.gamelive.navigation.LeafScreen
import com.cdle.gamelive.presentation.screens.detail.components.ImageWithLoadingState

@Composable
fun HomeScreen(navController: NavController, games: LazyPagingItems<Game>) {
    val context = LocalContext.current

    LaunchedEffect(
        key1 = games.loadState
    ) {
        if (games.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (games.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Hot games",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Start,
                )

            }

            if (LoadState.Error::class.java.isInstance(games.loadState.refresh)) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Error: " + (games.loadState.refresh as LoadState.Error).error.message,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            }


            if (games.loadState.refresh is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }

            } else {
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(games.itemCount) { index ->
                        games[index]?.let { game ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                shape = MaterialTheme.shapes.medium
                            )
                            {
                                GameItem(
                                    game = game,
                                    navController = navController
                                )
                            }
                        }
                    }
                    item {
                        if (games.loadState.append is LoadState.Loading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun GameItem(
    game: Game,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(LeafScreen.HomeDetail.createRoute(game.id))
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        game.backgroundImage?.let { ImageWithLoadingState(imageUrl = it) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.15f))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            game.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}



