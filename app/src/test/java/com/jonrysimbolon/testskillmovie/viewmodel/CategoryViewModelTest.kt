package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryModel
import com.jonrysimbolon.testskillmovie.repository.category.CategoryRepository
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
class CategoryViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    @Test
    fun `when Get Category Should Not Null and Return Data`() = runBlocking {
        val dummyCategory = DataDummy.generateDummyCategoryResponse()
        Mockito.`when`(categoryRepository.getAll()).thenReturn(dummyCategory)
        val categoryViewModel = CategoryViewModel(categoryRepository)
        val actualCategory = categoryViewModel.category.getOrAwaitValue()
        Assert.assertTrue(actualCategory is ResultStatus.Success)
        Assert.assertEquals(dummyCategory.size, (actualCategory as ResultStatus.Success).data.size)
        Assert.assertEquals(dummyCategory[0], actualCategory.data[0])
    }

    @Test
    fun `when Get Category Empty Should Return No Data`() = runBlocking {
        val data = emptyList<CategoryModel>()
        Mockito.`when`(categoryRepository.getAll()).thenReturn(data)
        val categoryViewModel = CategoryViewModel(categoryRepository)
        val actualCategory = categoryViewModel.category.getOrAwaitValue()

        Assert.assertTrue(actualCategory is ResultStatus.Success)
        Assert.assertEquals(0, (actualCategory as ResultStatus.Success).data.size)
    }
}