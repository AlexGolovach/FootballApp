package com.renovavision.footballapp.ui.utils

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

val defaultSubscribeOnScheduler: Scheduler = Schedulers.io()
val defaultOnError: (Throwable) -> Unit =
    { RxJavaPlugins.onError(OnErrorNotImplementedException(it)) }
val defaultOnComplete: () -> Unit = {}
val defaultOnNext: (T: Any) -> Unit = {}

@JvmOverloads
fun <T : Any> Observable<T>.sub(
    distinctUntilChanged: Boolean = false,
    subscribeOn: Scheduler = defaultSubscribeOnScheduler,
    observeOn: Scheduler? = null,
    onError: (Throwable) -> Unit = defaultOnError,
    onComplete: () -> Unit = defaultOnComplete,
    onNext: (T) -> Unit = defaultOnNext,
    addTo: CompositeDisposable? = null
): Disposable = run {
    when {
        distinctUntilChanged -> distinctUntilChanged()
        else -> this
    }
}.subscribeOn(subscribeOn).run {
    when (observeOn) {
        null -> this
        else -> observeOn(observeOn)
    }
}.subscribeBy(
    onError = onError,
    onComplete = onComplete,
    onNext = onNext
).apply {
    addTo?.let {
        addTo(it)
    }
}

@JvmOverloads
fun <T : Any> Single<T>.sub(
    subscribeOn: Scheduler = defaultSubscribeOnScheduler,
    observeOn: Scheduler? = null,
    onError: (Throwable) -> Unit = defaultOnError,
    onSuccess: (T) -> Unit = defaultOnNext,
    addTo: CompositeDisposable? = null
): Disposable = subscribeOn(subscribeOn).run {
    when (observeOn) {
        null -> this
        else -> observeOn(observeOn)
    }
}.subscribeBy(
    onError = onError,
    onSuccess = onSuccess
).apply {
    addTo?.let {
        addTo(it)
    }
}