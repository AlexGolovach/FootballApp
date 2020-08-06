package com.renovavision.footballapp.ui.uni

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.renovavision.footballapp.ui.utils.sub
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

abstract class UniFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    private val compositeDisposable = CompositeDisposable()

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

    protected fun <T : Any> Observable<T>.sub(
        distinctUntilChanged: Boolean = true,
        onNext: (T) -> Unit
    ) = sub(
        distinctUntilChanged = distinctUntilChanged,
        observeOn = AndroidSchedulers.mainThread(),
        onNext = onNext,
        addTo = compositeDisposable
    )
}