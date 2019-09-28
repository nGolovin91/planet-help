package com.yoite.planethelp.data.repository


interface OnDataListener<in T> {
    fun onData(t: T?, e: ErrorModel?)
}