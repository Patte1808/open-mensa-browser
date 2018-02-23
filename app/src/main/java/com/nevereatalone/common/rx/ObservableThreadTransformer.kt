package com.nevereatalone.common.rx

import android.support.annotation.VisibleForTesting
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


interface ObservableThreadTransformer {

    fun <T> apply(): ObservableTransformer<T, T>

    companion object {
        @VisibleForTesting
        val TEST: ObservableThreadTransformer = object : ObservableThreadTransformer {
            override fun <T> apply(): ObservableTransformer<T, T> {
                return ObservableTransformer { upstream ->
                    upstream.subscribeOn(Schedulers.trampoline())
                            .observeOn(Schedulers.trampoline())
                }
            }
        }
    }
}

class ObservableThreadTransformerImpl @Inject constructor() : ObservableThreadTransformer {
    override fun <T> apply(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}