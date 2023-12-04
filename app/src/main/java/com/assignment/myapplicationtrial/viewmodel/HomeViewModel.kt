package com.assignment.myapplicationtrial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    private val _mapStyle = MutableLiveData<Int>()
    val mapStyle: LiveData<Int> = _mapStyle

    fun setMapStyleRes(resId: Int) {
        _mapStyle.value = resId
    }
}