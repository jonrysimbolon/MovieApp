package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jonrysimbolon.testskillmovie.data.remote.model.VideoModel
import com.jonrysimbolon.testskillmovie.repository.trailer.TrailerRepository
import com.jonrysimbolon.testskillmovie.utils.DataDummy
import com.jonrysimbolon.testskillmovie.utils.MainDispatcherRule
import com.jonrysimbolon.testskillmovie.utils.ResultStatus
import com.jonrysimbolon.testskillmovie.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TrailerViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var trailerRepository: TrailerRepository

    @Test
    fun `when Get Trailer Should Not Null and Return Data`() = runBlocking {
        val dummyTrailer = DataDummy.generateDummyVideoResponse()
        Mockito.`when`(trailerRepository.getAll(0)).thenReturn(dummyTrailer)
        val trailerViewModel = TrailerViewModel(trailerRepository)
        trailerViewModel.getAllVideos(0)
        val actualTrailer = trailerViewModel.trailer.getOrAwaitValue()
        Assert.assertTrue(actualTrailer is ResultStatus.Success)
        Assert.assertEquals(dummyTrailer.size, (actualTrailer as ResultStatus.Success).data.size)
        Assert.assertEquals(dummyTrailer[0], actualTrailer.data[0])
    }

    @Test
    fun `when Get Trailer Empty Should Return No Data`() = runBlocking {
        val data = emptyList<VideoModel>()
        Mockito.`when`(trailerRepository.getAll(0)).thenReturn(data)
        val trailerViewModel = TrailerViewModel(trailerRepository)
        trailerViewModel.getAllVideos(0)
        val actualTrailer = trailerViewModel.trailer.getOrAwaitValue()
        Assert.assertTrue(actualTrailer is ResultStatus.Success)
        Assert.assertEquals(0, (actualTrailer as ResultStatus.Success).data.size)
    }
}