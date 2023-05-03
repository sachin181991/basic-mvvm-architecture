package com.basic.architecture.utils

import com.basic.architecture.utils.Constants.ConstantValues.VERSION

object Constants {

    //Constant value
    object ConstantValues {
        const val EMPTY_STRING = ""
        const val X_API_KEY = "x-api-key"
        const val X_API_VALUE = "acde187a-cbce-485f-8e67-caf67642b596"
        const val VERSION = "v1"
        const val BREED_DETAILS = "breed_details"
    }

    object ScreenNames

    //Pref Manager Values
    object PrefValues {
        const val PREF_NAME = "pref_name"
    }

    //API Calling
    object ApiValues {
        const val BASE_URL = "https://api.thecatapi.com/${VERSION}/"
        const val BREEDS = "breeds"
    }

}