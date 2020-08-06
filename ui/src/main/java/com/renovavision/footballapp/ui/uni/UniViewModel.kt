package com.renovavision.footballapp.ui.uni

import androidx.lifecycle.ViewModel
import com.renovavision.footballapp.ui.utils.defaultOnComplete
import com.renovavision.footballapp.ui.utils.defaultOnError
import com.renovavision.footballapp.ui.utils.defaultOnNext
import com.renovavision.footballapp.ui.utils.sub
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.ReplaySubject
import io.reactivex.subjects.Subject

abstract class UniViewModel<S : Any>(private val scheduler: Scheduler = Schedulers.io()) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val stateSubject: Subject<S> = BehaviorSubject.create<S>().toSerialized()
    private val actionsSubject: Subject<Action> = ReplaySubject.create<Action>().toSerialized()

    val state: Observable<S> = stateSubject.hide()

    init {
        actionsSubject.observeOn(scheduler)
            .scan(initialState(),
                { state, action -> reduce(state, action) }
            ).subscribe(stateSubject)
    }

    protected abstract fun initialState(): S // return initial state
    protected abstract fun reduce(prevState: S, action: Action): S // reduce state on main context
    protected abstract fun async(prevState: S, asyncAction: AsyncAction) // run async action

    fun dispatch(dispatchable: Dispatchable) {
        when (dispatchable) {
            is Action -> actionsSubject.onNext(dispatchable)
            is AsyncAction -> compositeDisposable.add(
                stateSubject.firstElement()
                    .flatMapCompletable {
                        Completable.fromAction { async(it, dispatchable) }
                    }.doOnError(RxJavaPlugins::onError)
                    .onErrorComplete()
                    .subscribeOn(scheduler)
                    .subscribe()
            )
        }
    }

    @JvmOverloads
    protected fun <T : Any> Observable<T>.sub(
        onError: (Throwable) -> Unit = defaultOnError,
        onComplete: () -> Unit = defaultOnComplete,
        onNext: (T) -> Unit = defaultOnNext
    ) = sub(
        onError = onError,
        onComplete = onComplete,
        onNext = onNext,
        addTo = compositeDisposable
    )

    @JvmOverloads
    protected fun <T : Any> Single<T>.sub(
        onError: (Throwable) -> Unit = defaultOnError,
        onSuccess: (T) -> Unit = defaultOnNext
    ) = sub(
        onError = onError,
        onSuccess = onSuccess,
        addTo = compositeDisposable
    )

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
