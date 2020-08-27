package com.wayne.paging3example.di

import com.wayne.paging3example.Constant
import com.wayne.paging3example.model.Repository
import com.wayne.paging3example.model.api.GithubApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { createOkHttpClient() }
    single { createEpaDataService(get()) }
    single { createRepository(get()) }
}

fun createOkHttpClient() : OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun createEpaDataService(okHttpClient: OkHttpClient): GithubApiService {
    val retrofit =  Retrofit.Builder()
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.GITHUB_API_URL)
        .build()
    return retrofit.create(GithubApiService::class.java)
}

fun createRepository(githubApiService: GithubApiService): Repository {
    return Repository(githubApiService)
}