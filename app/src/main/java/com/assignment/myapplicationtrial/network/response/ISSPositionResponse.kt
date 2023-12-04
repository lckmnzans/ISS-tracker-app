package com.assignment.myapplicationtrial.network.response

import com.google.gson.annotations.SerializedName

data class ISSPositionResponse(

	@field:SerializedName("iss_position")
	val issPosition: IssPositionData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("timestamp")
	val timestamp: Int
)

data class IssPositionData(

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("longitude")
	val longitude: Double
)
