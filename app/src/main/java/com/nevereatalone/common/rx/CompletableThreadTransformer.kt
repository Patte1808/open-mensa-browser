package com.nevereatalone.common.rx

import android.support.annotation.VisibleForTesting
import io.reactivex.CompletableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface CompletableThreadTransformer {

    fun apply(): CompletableTransformer

    companion object {
        @VisibleForTesting
        val TEST: CompletableThreadTransformer = object : CompletableThreadTransformer {
            override fun apply(): CompletableTransformer {
                return CompletableTransformer { upstream ->
                    upstream.subscribeOn(Schedulers.trampoline())
                            .observeOn(Schedulers.trampoline())
                }
            }
        }
    }
}

class CompletableThreadTransformerImpl @Inject constructor() : CompletableThreadTransformer {
    override fun apply(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}