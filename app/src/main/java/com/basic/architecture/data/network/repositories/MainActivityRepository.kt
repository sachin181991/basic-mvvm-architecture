package com.basic.architecture.data.network.repositories

import android.content.Context
import com.basic.architecture.data.SafeApiRequest
import com.basic.architecture.utils.selectApi

class MainActivityRepository(
    private val context: Context
) : SafeApiRequest() {
    suspend fun getBreedsCall(pageCount: String, listLimit: String) = apiRequest {
        context.selectApi()?.getBreedsCall(pageCount, listLimit)
    }
}