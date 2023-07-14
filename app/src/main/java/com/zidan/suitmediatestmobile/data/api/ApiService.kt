package com.zidan.suitmediatestmobile.data.api

import com.zidan.suitmediatestmobile.data.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun userList(
        @Query("per_page") per_page : Int,
        @Query("page") page : Int,
        @Query("delay") delay : Int,
    ): Response<ApiResponse>
}