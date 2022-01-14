package com.example.shopapp.data.api

import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {
    companion object {
        private const val API_KEY = "61ddae2e95cb716ea5ee48e4"
        private const val HEADER_ACCEPT = "accept"
        private const val HEADER_ACCEPT_VALUE = "application/json"
        private const val HEADER_NAME_KEY = "x-apikey"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request =
            request
                .newBuilder()
                .addHeader(HEADER_ACCEPT, HEADER_ACCEPT_VALUE)
                .addHeader(HEADER_NAME_KEY, API_KEY)
                .build()
        return chain.proceed(request)
    }
}