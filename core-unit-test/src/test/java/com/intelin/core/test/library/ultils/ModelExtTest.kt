package com.intelin.core.test.library.ultils

import com.intelin.core.library.extension.createStateFlow
import com.intelin.core.library.extension.resetValue
import com.intelin.core.library.extension.setValueFalse
import com.intelin.core.library.extension.setValueTrue
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Test

class ModelExtTest {

    @Test
    fun stateFlowModel_SetValueTrueTest() {
        val value = MutableStateFlow(false)
        val valueLocal = value.createStateFlow()
        valueLocal.setValueTrue()
        assertTrue(value.value)
    }

    @Test
    fun stateFlowModel_SetValueFalseTest() {
        val value = MutableStateFlow(true)
        val valueLocal = value.createStateFlow()
        valueLocal.setValueFalse()
        assertFalse(value.value)
    }

    @Test
    fun stateFlowModel_ResetValueTest() {
        val value = MutableStateFlow(true)
        val valueLocal = value.createStateFlow()
        valueLocal.setValueFalse()
        valueLocal.resetValue()
        assertTrue(value.value)
    }
}