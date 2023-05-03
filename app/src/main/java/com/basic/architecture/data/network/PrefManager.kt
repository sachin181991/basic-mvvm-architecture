package com.basic.architecture.data.network

import android.content.Context
import android.content.SharedPreferences
import com.basic.architecture.utils.Constants

class PrefManager(context: Context?) {

    private val pref: SharedPreferences? =
        context?.getSharedPreferences(Constants.PrefValues.PREF_NAME, 0)
    private val editor: SharedPreferences.Editor? = pref?.edit()

    fun savePrefValue(key: String?, value: String) {
        editor?.putString(key, value)
        editor?.commit()
        editor?.apply()
    }

    fun savePrefValue(key: String?, value: Int) {
        editor?.putInt(key, value)
        editor?.commit()
        editor?.apply()
    }

    fun savePrefValue(key: String?, value: Boolean) {
        editor?.putBoolean(key, value)
        editor?.commit()
        editor?.apply()
    }

    fun getPrefValue(key: String?): String {
        return pref?.getString(key, Constants.ConstantValues.EMPTY_STRING).toString()
    }
}