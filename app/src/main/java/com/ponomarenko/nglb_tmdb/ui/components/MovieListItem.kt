package com.ponomarenko.nglb_tmdb.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ponomarenko.nglb_tmdb.R
import com.ponomarenko.nglb_tmdb.domain.model.Movie
import com.ponomarenko.nglb_tmdb.ui.theme.NGLB_TMDBTheme
import com.ponomarenko.nglb_tmdb.util.toGermanFormattedString
import com.ponomarenko.nglb_tmdb.util.toLocalDate

@Composable
fun MovieListItem(
    movie: Movie,
    placeholder: Painter,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = { onClick(movie.id) },
        modifier = modifier
            .fillMaxWidth()
            .height(142.dp)
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = "Movie Poster",
                placeholder = placeholder,
                error = placeholder,
                modifier = Modifier
                    .size(width = 95.dp, height = 142.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )

                Text(
                    text = movie.releaseDate.toLocalDate()
                        ?.toGermanFormattedString() ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 6.dp),
                    maxLines = 1
                )

                movie.rating?.let {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "%.1f".format(it),
                            modifier = Modifier.padding(start = 4.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListItemPreview() {
    NGLB_TMDBTheme {
        val placeholder = painterResource(id = R.drawable.placeholder_movie)
        MovieListItem(
            movie = Movie(
                id = 1,
                title = "Movie Title",
                posterPath = "/6nUfVSFKYeDSZWy3ZmtQz60GRY4.jpg",
                releaseDate = "2023-09-15",
                rating = 7.2f
            ),
            placeholder = placeholder,
            onClick = {}
        )
    }
}