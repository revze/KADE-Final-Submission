package io.revze.footballapp.utils

import org.junit.Test

import org.junit.Assert.*

class HelperTest {

    private val helper = Helper("2018-05-19 01:30:00+00:00")

    @Test
    fun convertDate() {
        assertEquals("Sat, 19 May 2018", helper.convertDate())
    }

    @Test
    fun convertTime() {
        assertEquals("08:30", helper.convertTime())
    }
}