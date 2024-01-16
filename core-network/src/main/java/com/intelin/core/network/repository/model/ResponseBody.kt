package com.intelin.core.network.model

data class ResponseEvent<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val error: Throwable? = null
) {

    enum class Status {
        SUCCESS,
        ERROR,
        START,
        FINALLY
    }

    companion object {
        fun <T> success(data: T): ResponseEvent<T> {
            return ResponseEvent(
                status = Status.SUCCESS,
                data = data)
        }

        fun <T> error(message: String, error: Throwable?): ResponseEvent<T> {
            return ResponseEvent(
                status = Status.ERROR,
                message =  "Error: $message",
                error = error)
        }

        fun start(): ResponseEvent<Nothing> {
            return ResponseEvent(status = Status.START)
        }

        fun finally(): ResponseEvent<Nothing> {
            return ResponseEvent(status = Status.FINALLY)
        }
    }
}

data class ResponseBody<T>(
    val data: T?,
    val messages: String?,
    val code: String
)