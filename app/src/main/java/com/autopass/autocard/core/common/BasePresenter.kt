package com.autopass.autocard.core.common

import androidx.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter : BaseObservable() {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

    companion object {
        const val AUTOCARD_ERRORS = "AUTOCARD_ERRORS"
    }

    var loading : Boolean = false
        set(value) {
            field = value
            notifyChange()
        }

}