package com.wayne.paging3example.model.api

import com.wayne.paging3example.model.vo.User
import com.wayne.paging3example.model.vo.UserDetail
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/users")
    fun getUsers(@Query("since") since: String): Call<List<User>>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/users/{username}")
    fun getUserDetail(@Path("username") login: String): Single<UserDetail>
}