package com.wayne.paging3example.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.KoinComponent

abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application), KoinComponent {
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    protected fun Disposable.disposeOnDestroy() {
        disposables.add(this)
    }
}