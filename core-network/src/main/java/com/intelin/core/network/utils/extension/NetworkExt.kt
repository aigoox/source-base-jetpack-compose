package com.intelin.core.network.utils.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.intelin.core.network.model.ResponseBody
import com.intelin.core.network.model.ResponseEvent
import kotlinx.coroutines.Dispatchers
import okio.IOException
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

inline fun <reified T> performNetworkCall(context: CoroutineContext = Dispatchers.IO, crossinline networkCall: suspend () -> T): LiveData<ResponseEvent<T>> {
    return liveData(context) {
        emit(ResponseEvent.start())
        try {
            val data = networkCall.invoke()
            emit(ResponseEvent.success(data))
        }
        catch (e: HttpException) {
            try {
                val parseModel: T = Gson().fromJson(e.response()?.errorBody()?.string(), T::class.java)
                emit(ResponseEvent.success(parseModel))
            }catch (g: Throwable) {
                emit(ResponseEvent.error(
                    message = g.message.toString(),
                    error = Throwable(g.message, g.cause))
                )
            }
        }
        catch (e: IOException) {
            emit(ResponseEvent.error(
                message = e.message ?: "",
                error = Throwable(e.message, e.cause)))
        }
        finally {
            emit(ResponseEvent.finally())
        }
    }
}

/**
 * Start Api
 */
fun <T> ResponseEvent<T>.onStart(callback: () -> Unit) {
    if (this.status === ResponseEvent.Status.START) {
        callback.invoke()
    }
}

/**
 * Call api success from server
 */
fun <T> ResponseEvent<T>.onSuccess(callback: (T) -> Unit): ResponseEvent<T> {
    if (this.status === ResponseEvent.Status.SUCCESS) {
        this.data?.let { callback.invoke(it) }
    }
    return this
}

/**
 * Error api , network , status code != 200
 */
fun <T> ResponseEvent<T>.onError(callback: (e: Throwable) -> T): ResponseEvent<T> {
    if (this.status === ResponseEvent.Status.ERROR) {
        this.error?.let { callback.invoke(it) }
    }
    return this
}

/**
 * Before api about success and failed
 */
fun <T> ResponseEvent<T>.onFinally(callback: () -> Unit) {
    if (this.status === ResponseEvent.Status.FINALLY) {
        callback.invoke()
    }
}

/**
 * function on onSuccess - status api success
 */
fun <T> ResponseBody<T>.onData(action: (T) -> Unit): ResponseBody<T> {
    if (this.code == "200") {
        this.data?.let{
            action.invoke(it)
        }
    }
    return this
}

/**
 * function on onSuccess - status api failed
 */
fun <T> ResponseBody<T>.onFailed(action: (String) -> Unit): ResponseBody<T> {
    if (this.code != "200") {
        this.messages?.let {
            action.invoke(it)
        }
    }
    return this
}