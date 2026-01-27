package com.ponomarenko.nglb_tmdb.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ponomarenko.nglb_tmdb.domain.model.Movie
import com.ponomarenko.nglb_tmdb.ui.components.MovieListItem
import com.ponomarenko.nglb_tmdb.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val moviesPagingItems: LazyPagingItems<Movie> = viewModel.popularMovies.collectAsLazyPagingItems()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "Neugelb TMDB",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Last movies",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                )
            }

            items(moviesPagingItems.itemCount) { index ->
                val movie = moviesPagingItems[index]
                movie?.let {
                    MovieListItem(movie = it)
                }
            }
        }
    }
}