package com.nevereatalone.data.local.shared_preferences

import android.content.Context

class SharedPreferencesService constructor(val context: Context) {

    val sharedPreferences by lazy {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun getOnboardingStatus() = getBoolean(ONBOARDING_STATUS)

    fun setOnboardingStatus(value: Boolean) {
        putBoolean(ONBOARDING_STATUS, value)
    }

    private fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    private fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

    companion object {
        const val PREFERENCES_NAME = "open_mensa"
        const val ONBOARDING_STATUS = "onboarding_status"
    }
}