package com.yoite.planethelp.data.api.core

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class DefaultHttpClient(defaultInterceptor: Interceptor) {

    companion object {
        private const val TIME_OUT_CONNECT = 10L
        private const val TIME_OUT_WRITE = 10L
        private const val TIME_OUT_READ = 0L
    }

    private var httpClient: OkHttpClient? = null

    fun getHttpClient(): OkHttpClient = httpClient!!

    init {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(defaultInterceptor)
            connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS)
            readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
            hostnameVerifier { _, _ -> true }
        }

        httpClient = builder.build()
    }

    fun destroy() {
        httpClient = null
    }

}