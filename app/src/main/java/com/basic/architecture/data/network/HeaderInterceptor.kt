package com.basic.architecture.data.network

import android.content.Context
import com.basic.architecture.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
    private val context: Context?
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .header(Constants.ConstantValues.X_API_KEY, Constants.ConstantValues.X_API_VALUE)
                .build()
        )
    }
}