package com.jonrysimbolon.base.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jonrysimbolon.base.model.BaseModel

abstract class BasePagingSource<I, T:BaseModel<I>> : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    abstract suspend fun baseLoad(page: Int): List<T>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = baseLoad(page)
            LoadResult.Page(
                data = responseData,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (responseData.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}
