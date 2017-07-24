package com.company.db.base

import org.junit.Assert
import org.junit.Test
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * @author Nikita Gorodilov
 */
class TestExtensions: Assert() {

    /* дата */
    private val dateStr = "2017-07-24 16:15:29"
    private val date = SimpleDateFormat(SQLITE_DATE_FORMAT).parse(dateStr)

    /* isDeleted */
    private val isDeletedStr = "N"
    private val isDeleted = false

    @Test
    fun testDateToSqlite() {
        assertEquals(dateStr, date.toSqlite())
    }

    @Test
    fun testStringToDate() {
        assertEquals(date, dateStr.fromSqlite())
    }

    @Test
    fun testIsDeletedToSqlite() {
        assertEquals(isDeletedStr, isDeleted.toSqlite())
    }

    @Test
    fun testStringIsDeletedToBoolean() {
        assertEquals(isDeleted, isDeletedStr.toIsDeleted())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testErrorParseStringIsDeleted() {
        "error".toIsDeleted()
    }

    @Test(expected = ParseException::class)
    fun testErrorParseDateIsSqlite() {
        "error".fromSqlite()
    }
}