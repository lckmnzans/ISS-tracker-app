package com.assignment.myapplicationtrial.network

import android.os.Handler
import com.assignment.myapplicationtrial.network.response.ISSPosition
import com.assignment.myapplicationtrial.network.response.ISSPositionResponse
import com.assignment.myapplicationtrial.utils.ISSPositionLiveData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ISSTracker {
    private val httpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.open-notify.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    private val issApiService = retrofit.create(ISSApiService::class.java)

    private fun startTracking() {
        moveISS()
    }

    suspend fun getISSPosition(): ISSPosition? {
        startTracking()
        return try {
            val response = issApiService.getISSPosition().execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    val lat = responseBody.issPosition.latitude
                    val lon = responseBody.issPosition.longitude
                    ISSPosition(lat, lon)
                }
                null
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun moveISS() {
        issApiService.getISSPosition().enqueue(object : Callback<ISSPositionResponse> {
            override fun onResponse(call: Call<ISSPositionResponse>, response: Response<ISSPositionResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        updateMap(it.issPosition.latitude, it.issPosition.longitude)
                    }
                }
                // Repeat the request after 5000 milliseconds (5 seconds)
                moveISS()
            }

            override fun onFailure(call: Call<ISSPositionResponse>, t: Throwable) {
                t.printStackTrace()
                // Repeat the request after 5000 milliseconds (5 seconds)
                moveISS()
            }
        })
    }

    fun updateMap(latitude: Double, longitude: Double) {
        ISSPositionLiveData.setISSPosition(ISSPosition(latitude, longitude))
    }

    companion object {
        const val TRACKER = "iss-tracker"
    }
}