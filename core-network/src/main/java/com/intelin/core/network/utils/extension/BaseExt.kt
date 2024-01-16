package com.intelin.core.network.utils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.intelin.core.library.extension.setValueFalse
import com.intelin.core.library.extension.setValueTrue
import com.intelin.core.library.repository.model.StateFlowModelInstance
import com.intelin.core.network.model.ResponseBody
import com.intelin.core.network.model.ResponseEvent

fun <T> LiveData<out ResponseEvent<ResponseBody<out T>>>.apiServiceData(
    lifecycleOwner: LifecycleOwner,
    loading: StateFlowModelInstance<Boolean>,
    messageNotify: StateFlowModelInstance<String>,
    startLoading: Boolean = true,
    endLoading: Boolean = true,
    data: (T) -> Unit = {}) {
    this.observe(lifecycleOwner) {

        it.onStart {

            if (startLoading) {

                loading.setValueTrue()
            }
        }

        it.onSuccess { responseBody ->

            responseBody.onData { dataLocal ->

                data.invoke(dataLocal)
            }

            responseBody.onFailed { message ->

                messageNotify.setValue(message)
            }
        }

        it.onError { error ->

            error.message?.let { message -> messageNotify.setValue(message) }
        }

        it.onFinally {

            if (endLoading) {

                loading.setValueFalse()
            }
        }
    }
}