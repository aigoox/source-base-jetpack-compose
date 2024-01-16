package com.intelin.core.test.library.ultils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.intelin.core.library.extension.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Assert.*
import org.junit.Test

class StateExtTest {

    @SuppressLint("ComposableNaming")
    @Composable
    @Test
    fun stateFlowGetValueTest() {
        val result = "abc"
        val value = MutableStateFlow(result)
        val valueLocal = value.asStateFlow()
        assertEquals(result, valueLocal.getValue())
    }
}