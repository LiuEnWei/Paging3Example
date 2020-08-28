package com.wayne.paging3example.ui.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wayne.paging3example.model.Repository
import com.wayne.paging3example.model.vo.UserDetail
import com.wayne.paging3example.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.inject

class UserDetailViewModel: BaseViewModel() {
    private val repository: Repository by inject()
    private val _userDetail = MutableLiveData<UserDetail>()
    val userDetail: LiveData<UserDetail>
        get() = _userDetail

    private val _errorMessage = MutableLiveData<Throwable>()
    val errorMessage: LiveData<Throwable>
        get() = _errorMessage

    fun getUserDetail(login: String) {
        repository.getUserDetail(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _userDetail.postValue(it)
            }
            .doOnError {
                _errorMessage.postValue(it)
            }
            .subscribe()
            .disposeOnDestroy()
    }
}