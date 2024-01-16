package com.intelin.core.test.library.entension

import com.intelin.core.library.extension.censoring
import com.intelin.core.library.extension.compareLengthThanOrEqual
import com.intelin.core.library.extension.getFormat
import com.intelin.core.library.extension.hasVietnameseAccent
import com.intelin.core.library.extension.isNotNull
import com.intelin.core.library.extension.isNull
import com.intelin.core.library.extension.parseModel
import com.intelin.core.library.extension.switch
import com.intelin.core.library.extension.toDate
import com.intelin.core.library.extension.toJson
import com.intelin.core.library.repository.data.FormatDate
import com.intelin.core.library.repository.data.TypeFormatString
import com.intelin.core.library.ultils.AppUtils
import com.intelin.core.test.library.repository.DataTest
import org.junit.Assert.*
import org.junit.Test

class AppExtTest {

    @Test
    fun stringWitch() {
        val value: String? = null
        val result = "abc"
        assertEquals(result, value.switch(result))
    }

    @Test
    fun unitNullTest() {
        val value: Unit? = null
        assertFalse(value.isNotNull())
        assertTrue(value.isNull())
    }

    @Test
    fun parseJsonTest() {
        val model = DataTest("abc",123, true)
        val result = AppUtils.gson.toJson(model)
        val value = model.toJson()
        assertEquals(result, value)
    }

    @Test
    fun stringToModelTest() {
        val string = "{'paramString': 'abc', 'paramInt': 123, 'paramBoolean': true}"
        val result = DataTest("abc",123, true)
        val value = string.parseModel<DataTest>()
        assertEquals(result, value)
    }

    @Test
    fun booleanTest() {
        val value: Boolean? = null
        assertTrue(value.switch(true))
        assertFalse(value.switch(false))
        assertTrue(true.switch(false))
        assertTrue(true.switch(true))
    }

    @Test
    fun compareLengthThanOrEqualTest() {
        val value = "aaaaaa"
        assertFalse(value.compareLengthThanOrEqual(10))
        assertTrue(value.compareLengthThanOrEqual(3))
        assertTrue(value.compareLengthThanOrEqual(6))
    }

    @Test
    fun hasVietnameseAccentTest() {
        val valueVI = "anh yêu em"
        val valueEn = "anh yeu em"
        assertTrue(valueVI.hasVietnameseAccent())
        assertFalse(valueEn.hasVietnameseAccent())
    }

    @Test
    fun stringGetFormatTest() {
        val value1 = "123123123"
        val value2 = "fahfasf.@com"
        val value3 = "fahfasf@.com"
        val value4 = "12312asfas@3123"
        val value5 = "12312asfas.3123"
        val value6 = "12312asfas3123"
        val resultEmail = TypeFormatString.EMAIL
        val resultNumber = TypeFormatString.NUMBER
        val resultNormal = TypeFormatString.NORMAL
        /**
         * Test email
         */
        assertNotEquals(resultEmail, value1.getFormat())
        assertNotEquals(resultEmail, value2.getFormat())
        assertEquals(resultEmail, value3.getFormat())
        assertNotEquals(resultEmail, value4.getFormat())
        assertNotEquals(resultEmail, value5.getFormat())
        assertNotEquals(resultEmail, value6.getFormat())

        /**
         * Test number
         */
        assertEquals(resultNumber, value1.getFormat())
        assertNotEquals(resultNumber, value2.getFormat())
        assertNotEquals(resultNumber, value3.getFormat())
        assertNotEquals(resultNumber, value4.getFormat())
        assertNotEquals(resultNumber, value5.getFormat())
        assertNotEquals(resultNumber, value6.getFormat())

        /**
         * Test normal
         */
        assertNotEquals(resultNormal, value1.getFormat())
        assertEquals(resultNormal, value2.getFormat())
        assertNotEquals(resultNormal, value3.getFormat())
        assertEquals(resultNormal, value4.getFormat())
        assertEquals(resultNormal, value5.getFormat())
        assertEquals(resultNormal, value6.getFormat())
    }

    @Test
    fun stringCensoringTest() {
        val value = "aaaaaaaaaa"
        val result1 = "aaa****aaa"
        val result2 = "aaaxxxxaaa"
        val result3 = "aaa***aaaa"
        val result4 = "aaaa****aa"
        val result5 = "a********a"

        assertEquals(result1, value.censoring(3,3))
        assertEquals(result2, value.censoring(3,3, 'x'))
        assertEquals(result3, value.censoring(3,4 ))
        assertEquals(result4, value.censoring(4,2))
        assertEquals(result5, value.censoring())
    }

    @Test
    fun longToDateTest() {
        val timestamp = 1683651600L
        /**
         * internationalFormat
         */
        assertEquals("2023-05-10", timestamp.toDate())
        assertEquals("2023-05-10 00:00:00", timestamp.toDate(FormatDate.DATETIME))
        assertEquals("00:00:00", timestamp.toDate(FormatDate.TIME))
        assertNotEquals("2023-5-10", timestamp.toDate())
        assertNotEquals("2023-5-10 0:0:0", timestamp.toDate(FormatDate.DATETIME))
        assertNotEquals("2023-05-10 00:0:0", timestamp.toDate(FormatDate.DATETIME))
        assertNotEquals("2023-5-10 0:00:0", timestamp.toDate(FormatDate.DATETIME))
        assertNotEquals("2023-5-10 0:0:00", timestamp.toDate(FormatDate.DATETIME))
        assertNotEquals("0:0:0", timestamp.toDate(FormatDate.TIME))
        assertNotEquals("0:00:0", timestamp.toDate(FormatDate.TIME))
        assertNotEquals("0:0:00", timestamp.toDate(FormatDate.TIME))
        assertNotEquals("00:0:0", timestamp.toDate(FormatDate.TIME))

        /**
         * vnFormat
         */
        assertEquals("10-05-2023", timestamp.toDate(isInternational = false))
        assertEquals("10-05-2023 00:00:00", timestamp.toDate(FormatDate.DATETIME, false))
        assertEquals("00:00:00", timestamp.toDate(FormatDate.TIME,false))
        assertNotEquals("10-5-2023", timestamp.toDate(isInternational = false))
        assertNotEquals("10-5-2023 0:00:00", timestamp.toDate(FormatDate.DATETIME, false))
        assertNotEquals("0:0:00", timestamp.toDate(FormatDate.TIME,false))
    }
}