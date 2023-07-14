package com.zidan.suitmediatestmobile.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.zidan.suitmediatestmobile.data.api.ApiService
import com.zidan.suitmediatestmobile.data.paging.ListPagingSource
import com.zidan.suitmediatestmobile.data.response.DataItem
import com.zidan.suitmediatestmobile.helper.UserDb

class ListRepository (private val database: UserDb, private val apiService: ApiService) {

    fun getUserList(): LiveData<PagingData<DataItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 3
            ),
            pagingSourceFactory ={
                ListPagingSource(apiService)
            }
        ).liveData
    }
}