package com.yfve.common.util


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class TestViewModel : ViewModel() {
    var isVisible= MutableLiveData<Boolean>(true)
    var isVisible2= MutableLiveData<Boolean>(true)
}