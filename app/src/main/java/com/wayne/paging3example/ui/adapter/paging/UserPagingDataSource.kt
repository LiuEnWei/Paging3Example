package com.wayne.paging3example.ui.adapter.paging

import androidx.paging.rxjava2.RxPagingSource
import com.wayne.paging3example.model.Repository
import com.wayne.paging3example.model.vo.User
import com.wayne.paging3example.extensions.getUsersNextPageUrl
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException

class UserPagingDataSource: RxPagingSource<String, User>(), KoinComponent {
    private val repository: Repository by inject()

    override fun loadSingle(params: LoadParams<String>): Single<LoadResult<String, User>> {
        return Single.create<LoadResult<String, User>> {
            val since = params.key ?: ""
            val response = repository.getUsers(since).execute()
            if (response.isSuccessful) {
                val headers = response.headers()
                val users = response.body() ?: emptyList()
                val nextSince = headers.getUsersNextPageUrl()
                it.onSuccess(LoadResult.Page(users, null, nextSince))
            } else {
                it.onError(HttpException(response))
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn { e ->
                e.printStackTrace()
                LoadResult.Error(e)
            }
    }
}