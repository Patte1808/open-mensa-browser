package com.nevereatalone.common.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


interface RxDisposables {
    fun add(disposable: Disposable)
    fun clear()
}

internal class CompositeDisposables : RxDisposables {
    val compositeDisposable = CompositeDisposable()


    override fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun clear() {
        compositeDisposable.clear()
    }

}