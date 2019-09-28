package com.yoite.planethelp.data.api.core


interface OnApiResultListener<in T, in E> {
    fun onSuccess(responseScheme : T)
    fun onFailure(errorScheme : E)
}