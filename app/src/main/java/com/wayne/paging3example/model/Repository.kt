package com.wayne.paging3example.model

import com.wayne.paging3example.model.api.GithubApiService
import com.wayne.paging3example.model.vo.User
import com.wayne.paging3example.model.vo.UserDetail
import io.reactivex.Single
import retrofit2.Call

class Repository(private val githubApiService: GithubApiService) {

    fun getUsers(since: String): Call<List<User>> {
        return githubApiService.getUsers(since)
    }

    fun getUserDetail(login: String): Single<UserDetail> {
        return githubApiService.getUserDetail(login)
    }
}