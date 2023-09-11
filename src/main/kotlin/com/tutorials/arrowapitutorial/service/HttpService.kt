package com.tutorials.arrowapitutorial.service

import com.tutorials.arrowapitutorial.util.APIConstants
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

abstract class HttpService {

    open fun getHttpUrl(): HttpUrl.Builder {
        return HttpUrl.Builder()
            .scheme(APIConstants.SCHEME)
            .host(APIConstants.BASE_URL)
            .addEncodedPathSegment(APIConstants.GATEWAY)
            .addEncodedQueryParameter(APIConstants.PARAM_API_KEY, APIConstants.API_KEY)
    }

    open fun fetchData(httpUrl: HttpUrl, httpClient: OkHttpClient): Response {
        val request: Request = Request.Builder().url(httpUrl).build()
        return httpClient.newCall(request).execute()
    }
}