package com.yoite.planethelp.data.api.core.interceptors

import com.yoite.planethelp.di.app.AppScope
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


@AppScope
class DefaultInterceptor
@Inject constructor() : Interceptor {

    companion object {
        const val QUALIFIER = "DefaultInterceptor.QUALIFIER"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()

        builder.addHeader("Content-Type", "application/json")
        builder.addHeader("Accept", "application/json")

        return chain.proceed(builder.build())
    }

}