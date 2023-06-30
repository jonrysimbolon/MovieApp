package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jonrysimbolon.testskillmovie.adapter.UserReviewAdapter
import com.jonrysimbolon.testskillmovie.data.remote.model.UserReviewModel
import com.jonrysimbolon.testskillmovie.repository.review.ReviewRepository
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
class UserReviewViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var reviewRepository: ReviewRepository

    @Test
    fun `when Get Review Should Not Null and Return Data`() = runTest {
        val dummyReview = DataDummy.generateDummyUserReviewResponse()
        val data: PagingData<UserReviewModel> = ReviewPagingSource.snapshot(dummyReview)

        val expectedReview = MutableLiveData<PagingData<UserReviewModel>>()
        expectedReview.value = data
        Mockito.`when`(reviewRepository.getAllPagingData(0)).thenReturn(expectedReview)

        val reviewViewModel = UserReviewViewModel(reviewRepository)
        reviewViewModel.getAllReviews(0)
        val actualStory: PagingData<UserReviewModel> = reviewViewModel.userReviewLivePaging.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = UserReviewAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualStory)

        Assert.assertNotNull(differ.snapshot())
        Assert.assertEquals(dummyReview.size, differ.snapshot().size)
        Assert.assertEquals(dummyReview[0], differ.snapshot()[0])
    }

    @Test
    fun `when Get Review Empty Should Return No Data`() = runTest {
        val data: PagingData<UserReviewModel> = PagingData.from(emptyList())
        val expectedReview = MutableLiveData<PagingData<UserReviewModel>>()
        expectedReview.value = data
        Mockito.`when`(reviewRepository.getAllPagingData(0)).thenReturn(expectedReview)
        val reviewViewModel = UserReviewViewModel(reviewRepository)
        reviewViewModel.getAllReviews(0)
        val actualStory: PagingData<UserReviewModel> = reviewViewModel.userReviewLivePaging.getOrAwaitValue()
        val differ = AsyncPagingDataDiffer(
            diffCallback = UserReviewAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualStory)
        Assert.assertEquals(0, differ.snapshot().size)
    }
}


class ReviewPagingSource : PagingSource<Int, LiveData<List<UserReviewModel>>>() {
    companion object {
        fun snapshot(items: List<UserReviewModel>): PagingData<UserReviewModel> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<UserReviewModel>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<UserReviewModel>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}