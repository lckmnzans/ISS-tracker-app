package com.assignment.myapplicationtrial.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.myapplicationtrial.network.response.ISSPosition

object ISSPositionLiveData {
    private val _issPosition = MutableLiveData<ISSPosition>()
    val issPosition: LiveData<ISSPosition> = _issPosition

    fun setISSPosition(position: ISSPosition) {
        _issPosition.postValue(position)
    }
}