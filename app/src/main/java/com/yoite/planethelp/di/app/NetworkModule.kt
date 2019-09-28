package com.yoite.planethelp.di.app

import com.google.gson.Gson
import com.yoite.planethelp.data.api.ApiConstants
import com.yoite.planethelp.data.api.core.DefaultHttpClient
import com.yoite.planethelp.data.api.core.Parser
import com.yoite.planethelp.data.api.core.ParserImpl
import com.yoite.planethelp.data.api.core.interceptors.DefaultInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@AppScope
@Module
abstract class NetworkModule {

    @AppScope
    @Module
    companion object {

        @AppScope
        @Provides
        @JvmStatic
        fun provideGson(parser: Parser): Gson {
            return parser.getDefaultParser()
        }

        @AppScope
        @Provides
        @JvmStatic
        fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
        }

        // ===========================================================
        // Interceptors
        // ===========================================================

        @AppScope
        @Provides
        @JvmStatic
        fun provideDefaultHttpClient(
            @Named(DefaultInterceptor.QUALIFIER) interceptor: Interceptor
        ): DefaultHttpClient {
            return DefaultHttpClient(interceptor)
        }
    }

    // ===========================================================
    // Parsers
    // ===========================================================

    @AppScope
    @Binds
    abstract fun provideParser(parser: ParserImpl): Parser

    // ===========================================================
    // Interceptors
    // ===========================================================

    @AppScope
    @Binds
    @Named(DefaultInterceptor.QUALIFIER)
    abstract fun provideDefaultInterceptor(interceptor: DefaultInterceptor): Interceptor

}