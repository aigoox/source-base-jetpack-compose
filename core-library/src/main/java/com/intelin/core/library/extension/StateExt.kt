package com.intelin.core.library.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.intelin.core.library.repository.model.StateFlowModel
import com.intelin.core.library.repository.model.StateFlowModelInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

fun <T> MutableStateFlow<T>.createStateFlow(): StateFlowModel<T> {
    return StateFlowModel(
        getState = this.asStateFlow(),
        firstValue = this.value
    ) {
        this.value = it
    }
}

/**
 * Use non-data related variable declarations for security
 */
fun <T> MutableStateFlow<T>.createStateFlowInstance(): StateFlowModelInstance<T> {
    return StateFlowModelInstance(
        getState = this.asStateFlow(),
        firstValue = this.value,
        getInstance = this
    ) {
        this.value = it
    }
}

/**
 * Get value with change of State
 */
@Composable
fun <T> StateFlow<T>.getValue(): T {
    return this.collectAsState().value
}

fun <T>StateFlowModel<T>.getValue(): T {
    return this.getState.value
}

/**
 * Get value with change of State
 */
@Composable
fun <T>StateFlowModel<T>.getValueCompose(): T {
    return this.getState.collectAsState().value
}

fun <T>StateFlowModelInstance<T>.getValue(): T {
    return this.getState.value
}

@Composable
fun <T>StateFlowModelInstance<T>.getValueCompose(): T {
    return this.getState.collectAsState().value
}

fun MutableState<Boolean>.setValueTrue() {
    this.value = true
}

fun MutableState<Boolean>.setValueFalse() {
    this.value = false
}