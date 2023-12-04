package com.assignment.myapplicationtrial.network

import com.assignment.myapplicationtrial.network.response.ISSPosition
import com.assignment.myapplicationtrial.network.response.ISSPositionResponse
import retrofit2.Call
import retrofit2.http.Query

interface ISSApiService {
    @retrofit2.http.GET("iss-now.json")
    fun getISSPosition(
        @Query("callback") callback: String? = null
    ): Call<ISSPositionResponse>
}