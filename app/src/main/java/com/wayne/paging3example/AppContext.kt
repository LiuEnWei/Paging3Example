package com.wayne.paging3example

import android.app.Application
import com.wayne.paging3example.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppContext: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppContext)
            modules(apiModule)
        }
    }
}