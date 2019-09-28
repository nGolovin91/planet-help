package com.yoite.planethelp.utils.mvp


interface Presenter<V : View> {

    fun attachView(view: V)
    fun detachView()

    fun onDestroy()
}