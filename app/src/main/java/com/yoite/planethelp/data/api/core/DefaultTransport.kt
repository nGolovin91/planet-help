package com.yoite.planethelp.data.api.core

import com.yoite.planethelp.data.api.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class DefaultTransport(
    private val retrofitBuilder: Retrofit.Builder,
    private val serviceProvider: ServicesFactoryImpl,
    private val defaultHttpClient: DefaultHttpClient
) {

    // ===========================================================
    // Services Factory
    // ===========================================================

    protected fun <TServices> getRetrofitServices(tag: String, servicesClass: Class<TServices>): TServices {
        return serviceProvider.getService(tag, servicesClass, object : ServiceFactory<TServices> {
            override fun createService(): TServices {
                val retrofit = retrofitBuilder
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ApiConstants.BASE_API_URL)
                    .client(defaultHttpClient.getHttpClient())
                    .build()
                return retrofit.create(servicesClass)
            }
        })
    }
}