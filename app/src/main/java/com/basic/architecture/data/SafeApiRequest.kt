package com.basic.architecture.data

import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>?): T? {
        val response = call.invoke()
        return if (response != null) {
            if (response.isSuccessful) {
                response.body()
            } else {
                return null
            }
        } else {
            null
        }
    }
}