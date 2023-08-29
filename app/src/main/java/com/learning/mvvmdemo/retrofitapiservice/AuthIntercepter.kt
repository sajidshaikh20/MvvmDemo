package com.learning.mvvmdemo.retrofitapiservice

import okhttp3.Interceptor
import okhttp3.Response

class AuthIntercepter : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("X-RapidAPI-Key", "8548d4e4damshe73c2141d9df696p130d21jsn431b6f6fe293")
        return chain.proceed(request.build())
    }
}
