package com.ponomarenko.nglb_tmdb.ui.viewmodel

import com.ponomarenko.nglb_tmdb.domain.use_case.GetMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setUp() {
        getMovieDetailsUseCase = mockk()
        viewModel = MovieDetailsViewModel(getMovieDetailsUseCase)
    }

    @Test
    fun `getMovieDetails emits expected movie details`() {
        val movieId = 1
        val movieDetails = MovieDetails(
            id = movieId,
            title = "Test Movie",
            overview = "Test Overview",
            posterPath = "test/path.jpg",
            releaseDate = "2026-02-05",
            genres = listOf("Action", "Drama")
        )
        val expectedResult = Result.success(movieDetails)
        coEvery { getMovieDetailsUseCase.invoke(movieId) } returns flowOf(expectedResult)

        viewModel.getMovieDetails(movieId)

        assertEquals(expectedResult, viewModel.movieDetails.value)
    }
}
