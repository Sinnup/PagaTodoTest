package com.sinue.pagatodo.mx

import com.sinue.pagatodo.mx.utils.TimeUtils
import org.junit.Test
import org.junit.Assert.assertEquals

class TimeUtilsTest {

    val timeStamp: Long = 1611847753

    @Test
    fun timeStampToDateTest(){
        val dateTest: String? = TimeUtils.convertTimeStampMillisToLocal(timeStamp)
        assertEquals("28/01/2021 03:29", dateTest)
    }
}