package io.revze.footballapp.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class HelperTest {

    private val helper = Helper("2018-05-19 01:30:00+00:00")

    @Test
    fun testConvertDate() {
        assertEquals("Sat, 19 May 2018", helper.convertDate())
    }

    @Test
    fun testConvertTime() {
        assertEquals("08:30", helper.convertTime())
    }
}