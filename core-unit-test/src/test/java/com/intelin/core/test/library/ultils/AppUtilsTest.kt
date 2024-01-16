package com.intelin.core.test.library.ultils

import com.intelin.core.library.ultils.AppUtils
import org.junit.Assert.*
import org.junit.Test

class AppUtilsTest {

    @Test
    fun convertDateToTimeStampTest() {
        val result = 1683651600L
        val value = AppUtils.convertDateToTimeStamp(10, 5, 2023)
        assertEquals(result, value)
    }
}