package com.intelin.core.library.repository.interfaces

import android.content.SharedPreferences
import com.intelin.core.library.repository.remote.PreferenceImpl

interface IPreference {
    fun getPreference(): SharedPreferences
    fun getEditor(): SharedPreferences.Editor
    fun remove(key: String)
    fun clear()

    fun <T> getCountersOption(key: String): T
    fun <T> setCountersOption(key: String = PreferenceImpl.COUNTERS_OPTION, value: T)
}