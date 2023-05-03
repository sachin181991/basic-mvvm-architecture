package com.basic.architecture.data.network

import android.content.Context
import com.basic.architecture.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyBase {
    private var retrofit: Retrofit? = null

    fun getMyBase(context: Context?): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.ApiValues.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor(HeaderInterceptor(context)).build())
                .build()
        }
        return retrofit
    }
}