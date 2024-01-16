package com.intelin.core.library.extension

import com.intelin.core.library.repository.model.StateFlowModel
import com.intelin.core.library.repository.model.StateFlowModelInstance

fun StateFlowModel<Boolean>.setValueTrue() {
    this.setValue(true)
}

fun StateFlowModel<Boolean>.setValueFalse() {
    this.setValue(false)
}

fun <T>StateFlowModel<T>.resetValue() {
    if (this.firstValue != this.getValue()) {
        this.setValue(this.firstValue)
    }
}

fun StateFlowModelInstance<Boolean>.setValueTrue() {
    this.setValue(true)
}

fun StateFlowModelInstance<Boolean>.setValueFalse() {
    this.setValue(false)
}

fun <T>StateFlowModelInstance<T>.resetValue() {
    if (this.firstValue != this.getValue()) {
        this.setValue(this.firstValue)
    }
}