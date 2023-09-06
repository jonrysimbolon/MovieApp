package com.jonrysimbolon.base

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.jonrysimbolon.base.model.BaseModel


/**
 * @param pagingSourceFactoryImpl denote pagingSourceFactory,
 * with pageSize = 5 constantly
 */
fun <I, T: BaseModel<I>>getLiveDataPager(
    pagingSourceFactoryImpl: PagingSource<Int, T>
): LiveData<PagingData<T>> =
    Pager(
        config = PagingConfig(
            pageSize = 5
        ),
        pagingSourceFactory = {
            pagingSourceFactoryImpl
        },
    ).liveData