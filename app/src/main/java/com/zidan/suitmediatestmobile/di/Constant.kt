package com.zidan.suitmediatestmobile.di

import android.content.Context
import com.zidan.suitmediatestmobile.data.api.ApiConfig
import com.zidan.suitmediatestmobile.data.repository.ListRepository
import com.zidan.suitmediatestmobile.helper.UserDb

object Constant {
    fun getRepository(context: Context): ListRepository{
        val database = UserDb.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return ListRepository(database,apiService)
    }
}