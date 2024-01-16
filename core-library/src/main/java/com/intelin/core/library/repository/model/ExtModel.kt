package com.intelin.core.library.repository.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class StateFlowModelInstance<T>(
    val getState: StateFlow<T>,
    val getInstance: MutableStateFlow<T>,
    val firstValue: T,
    val setValue: (T) -> Unit,
)

data class StateFlowModel<T>(
    val getState: StateFlow<T>,
    val firstValue: T,
    val setValue: (T) -> Unit,
)

data class ThrowableModel(
    override var message: String? = null,
    override val cause: Throwable? = null
): Throwable(message, cause)