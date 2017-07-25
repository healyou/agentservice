package com.company.db.message

import com.company.db.core.message.MessageBodyType
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Тестирование фукнциональности класса MessageBodyType
 *
 * @author Nikita Gorodilov
 */
class MessageBodyTypeTest: Assert() {

    private lateinit var type: MessageBodyType

    /* Параметры */
    private val id = 1L
    private val code = MessageBodyType.Code.JSON
    private val name = "name"
    private val isDeleted = false

    @Before
    fun setup() {
        type = MessageBodyType(
                id,
                code,
                name,
                isDeleted
        )
    }

    @Test
    fun testTypeGetData() {
        assertEquals(id, type.id)
        assertEquals(code, type.code)
        assertEquals(name, type.name)
        assertEquals(isDeleted, type.isDeleted)
    }

    @Test
    fun testTypeSetDate() {
        /* новые значение */
        val newId = 2L
        val newCode = MessageBodyType.Code.JSON
        val newName = "newName"
        val newIsDeleted = true

        type.id = newId
        type.code = newCode
        type.name = newName
        type.isDeleted = newIsDeleted

        assertEquals(newId, type.id!!)
        assertEquals(newCode, type.code)
        assertEquals(newName, type.name)
        assertEquals(newIsDeleted, type.isDeleted)
    }
}