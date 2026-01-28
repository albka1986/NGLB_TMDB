package com.ponomarenko.nglb_tmdb.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ponomarenko.nglb_tmdb.R
import com.ponomarenko.nglb_tmdb.domain.model.MovieDetails
import com.ponomarenko.nglb_tmdb.ui.theme.NGLB_TMDBTheme
import com.ponomarenko.nglb_tmdb.ui.viewmodel.MovieDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    movieId: Int,
    onUpClick: () -> Unit,
    viewModel: MovieDetailsViewModel = koinViewModel(),
) {
    val movieDetailsResult by viewModel.movieDetails.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.movie_details_title)) },
                navigationIcon = {
                    IconButton(onClick = onUpClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.movie_details_back_description)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            movieDetailsResult?.let {
                it.fold(
                    onSuccess = { movieDetails ->
                        MovieDetailsContent(movieDetails)
                    },
                    onFailure = {
                        Text(stringResource(id = R.string.movie_details_error))
                    }
                )
            } ?: CircularProgressIndicator()
        }
    }
}

@Composable
fun MovieDetailsContent(movieDetails: MovieDetails) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AsyncImage(
            model = movieDetails.posterPath,
            contentDescription = stringResource(id = R.string.movie_details_poster_description),
            placeholder = painterResource(id = R.drawable.placeholder_movie),
            error = painterResource(id = R.drawable.placeholder_movie),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = movieDetails.title,
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = movieDetails.overview,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(id = R.string.movie_details_genres_label, movieDetails.genres.joinToString(", ")),
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(id = R.string.movie_details_release_date_label, movieDetails.releaseDate ?: ""),
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    NGLB_TMDBTheme {
        MovieDetailsScreen(
            movieId = 1,
            onUpClick = {}
        )
    }
}
