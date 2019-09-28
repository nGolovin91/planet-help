package com.yoite.planethelp.utils.mvp

import androidx.annotation.CallSuper
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import java.util.concurrent.ConcurrentHashMap


abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    override fun attachView(view: T) {
        super.attachView(view)
    }

    override fun detachView(view: T) {
        super.detachView(view)
    }

}