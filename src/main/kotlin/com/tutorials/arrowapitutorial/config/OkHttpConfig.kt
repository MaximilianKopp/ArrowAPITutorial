package com.tutorials.arrowapitutorial.config

import com.tutorials.arrowapitutorial.util.APIConstants
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File
import java.util.concurrent.TimeUnit

@Configuration
class OkHttpConfig {

    @Bean
    fun getOkHttpClient(): OkHttpClient? {
        return OkHttpClient.Builder()
            .cache(Cache(File("/"), (10 * 1024 * 1024).toLong()))
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build()
    }

    @Bean
    fun getHttpUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(APIConstants.SCHEME)
            .host(APIConstants.BASE_URL)
            .addEncodedPathSegment(APIConstants.GATEWAY)
            .addEncodedQueryParameter(APIConstants.PARAM_API_KEY, APIConstants.API_KEY).build()
    }
}