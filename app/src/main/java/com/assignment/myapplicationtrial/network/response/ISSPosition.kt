package com.assignment.myapplicationtrial.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ISSPosition(
    val latitude: Double,
    val longitude: Double
): Parcelable
