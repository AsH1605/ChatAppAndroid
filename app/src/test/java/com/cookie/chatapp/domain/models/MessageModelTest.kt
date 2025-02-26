package com.cookie.chatapp.domain.models

import org.junit.jupiter.api.Assertions.*
import com.cookie.chatapp.domain.models.fromStringToMessageTimestamp
import org.junit.Test
import org.junit.jupiter.api.fail

class MessageModelTest {

    @Test
    fun testDateParsingSuccessful() {
        val dateTime = "2025-02-25T20:01:48.121Z"
        val messageTimestamp = fromStringToMessageTimestamp(dateTime)
        assertEquals(2, messageTimestamp.month)
        assertEquals(25, messageTimestamp.date)
        assertEquals(20, messageTimestamp.hour)
        assertEquals(1, messageTimestamp.minute)
        assertEquals(2025, messageTimestamp.year)
    }

    @Test
    fun testSubstringSuccessful(){
        val substring = "ll"
        assertEquals(substring, substring("hello", 2, 3))
    }

    @Test
    fun testSubstringFailed(){
        try {
            substring("", 2, 3)
            fail("this should not be executed")
        }catch(e: Exception) {
            assertNotNull(e)
        }
    }
}