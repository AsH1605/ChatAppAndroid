package com.cookie.chatapp.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class IdTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val idToken = chain.request().headers["authorization"]
        val newRequest = chain.request().newBuilder().removeHeader("authorization").addHeader(
            "authorization",
            "Bearer $idToken"
        ).build()
        return chain.proceed(newRequest)
    }
}