package com.nevereatalone.common.rx

import android.support.annotation.VisibleForTesting
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


interface SingleThreadTransformer {

    fun <T> apply(): SingleTransformer<T, T>

    companion object {
        @VisibleForTesting
        val TEST: SingleThreadTransformer = object : SingleThreadTransformer {
            override fun <T> apply(): SingleTransformer<T, T> {
                return SingleTransformer { upstream ->
                    upstream.subscribeOn(Schedulers.trampoline())
                            .observeOn(Schedulers.trampoline())
                }
            }
        }
    }
}

class SingleThreadTransformerImpl @Inject constructor() : SingleThreadTransformer {
    override fun <T> apply(): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}