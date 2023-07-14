package com.zidan.suitmediatestmobile.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import com.zidan.suitmediatestmobile.data.api.ApiService
import com.zidan.suitmediatestmobile.data.response.DataItem
import java.lang.Exception

class ListPagingSource (private val apiService: ApiService) : PagingSource<Int, DataItem>() {


    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: (anchorPage?.nextKey?.minus(1))
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseDataList = apiService.userList(params.loadSize, position, 2   ).body()!!.data
            LoadResult.Page(
                data = responseDataList,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseDataList.isEmpty()) null else position + 1
            )
        } catch (ex: Exception){
            return LoadResult.Error(ex)
        }
    }

    companion object {
       const val INITIAL_PAGE_INDEX = 1
    }
}