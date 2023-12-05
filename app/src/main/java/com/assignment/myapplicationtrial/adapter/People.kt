package com.assignment.myapplicationtrial.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class People(
    val name: String,
    val nim: String
): Parcelable
