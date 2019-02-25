package com.base.viper.utils.validators

import android.util.Patterns
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single

class PatternsLoginValidates {
    companion object Factory {
        fun create(): PatternsLoginValidates =
            PatternsLoginValidates()
    }

    val verifyEmailPattern = ObservableTransformer<String, String> { observable ->
        observable.flatMap {
            Observable.just(it).map { it.trim() }
                .filter { Patterns.EMAIL_ADDRESS.matcher(it).matches() }
                .singleOrError()
                .onErrorResumeNext {
                    if (it is NoSuchElementException) {
                        Single.error(Exception("Email no valido"))
                    } else {
                        Single.error(it)
                    }
                }
                .toObservable()
        }
    }

    val lengthGreaterThanSix = ObservableTransformer<String, String> { observable ->
        observable.flatMap {
            Observable.just(it).map { it.trim() }
                .filter { it.length > 6 }
                .singleOrError()
                .onErrorResumeNext {
                    if (it is NoSuchElementException) {
                        Single.error(Exception("La longitud debe ser mayor de 6"))
                    } else {
                        Single.error(it)
                    }
                }.toObservable()
        }
    }
}