package com.intelin.core.library.base

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.intelin.core.library.extension.createStateFlowInstance
import com.intelin.core.library.extension.setValueFalse
import com.intelin.core.library.extension.setValueTrue
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
open class BaseViewModel
@Inject constructor(): ViewModel() {

    @Inject
    @ApplicationContext
    lateinit var context: Context

    open fun getString(@StringRes idResource: Int): String {
        return context.getString(idResource)
    }

    private val _loading = MutableStateFlow(false)
    val loading = _loading.createStateFlowInstance()

    private val _messageNotify = MutableStateFlow("")
    val messageNotify = _messageNotify.createStateFlowInstance()

    open fun showLoading() {
        loading.setValueTrue()
    }

    open fun hiddenLoading() {
        loading.setValueFalse()
    }

    open fun pushToast(message: String, mode: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, mode).show()
    }
    open fun pushToast(@StringRes id: Int, mode: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, getString(id), mode).show()
    }
    open fun getString(@StringRes idResource: Int, replace: String): String {
        return context.getString(idResource, replace)
    }

    open fun getString(@StringRes idResource: Int, @StringRes replaceID: Int): String {
        return context.getString(idResource, getString(replaceID))
    }

}