package com.wayne.paging3example.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.observable
import com.wayne.paging3example.model.vo.User
import com.wayne.paging3example.ui.adapter.paging.UserPagingDataSource
import com.wayne.paging3example.base.BaseViewModel

class UserListViewModel: BaseViewModel() {

    private val _userPaging = MutableLiveData<PagingData<User>>()
    val userPaging: LiveData<PagingData<User>>
        get() = _userPaging

    init {
        getUsers()
    }

    private fun getUsers() {
        Pager(PagingConfig(30)) {
            UserPagingDataSource()
        }.observable
            .cachedIn(viewModelScope)
            .subscribe {
                _userPaging.postValue(it)
            }
            .disposeOnDestroy()
    }
}