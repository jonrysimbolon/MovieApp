package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jonrysimbolon.testskillmovie.adapter.MovieAdapter
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel
import com.jonrysimbolon.testskillmovie.repository.movie.MovieRepository
import com.jonrysimbolon.testskillmovie.utils.DataDummy
import com.jonrysimbolon.testskillmovie.utils.MainDispatcherRule
import com.jonrysimbolon.testskillmovie.utils.getOrAwaitValue
import com.jonrysimbolon.testskillmovie.utils.noopListUpdateCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Test
    fun `when Get Movie Should Not Null and Return Data`() = runTest {
        val dummyMovie = DataDummy.generateDummyMovieResponse()
        val data: PagingData<MovieModel> = MoviePagingSource.snapshot(dummyMovie)

        val expectedMovie = MutableLiveData<PagingData<MovieModel>>()
        expectedMovie.value = data
        Mockito.`when`(movieRepository.getAllPagingData("")).thenReturn(expectedMovie)

        val movieViewModel = MovieViewModel(movieRepository)
        movieViewModel.getAllMovies("")
        val actualStory: PagingData<MovieModel> = movieViewModel.movieLivePaging.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = MovieAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualStory)

        Assert.assertNotNull(differ.snapshot())
        Assert.assertEquals(dummyMovie.size, differ.snapshot().size)
        Assert.assertEquals(dummyMovie[0], differ.snapshot()[0])
    }

    @Test
    fun `when Get Movie Empty Should Return No Data`() = runTest {
        val data: PagingData<MovieModel> = PagingData.from(emptyList())
        val expectedMovie = MutableLiveData<PagingData<MovieModel>>()
        expectedMovie.value = data
        Mockito.`when`(movieRepository.getAllPagingData("")).thenReturn(expectedMovie)
        val movieViewModel = MovieViewModel(movieRepository)
        movieViewModel.getAllMovies("")
        val actualStory: PagingData<MovieModel> = movieViewModel.movieLivePaging.getOrAwaitValue()
        val differ = AsyncPagingDataDiffer(
            diffCallback = MovieAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualStory)
        Assert.assertEquals(0, differ.snapshot().size)
    }
}


class MoviePagingSource : PagingSource<Int, LiveData<List<MovieModel>>>() {
    companion object {
        fun snapshot(items: List<MovieModel>): PagingData<MovieModel> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<MovieModel>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<MovieModel>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}