package com.wayne.paging3example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wayne.paging3example.base.BaseViewModel

class MainViewModel: BaseViewModel() {
    private val _isLoading = MutableLiveData<Boolean>().apply { this.postValue(false) }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }
}