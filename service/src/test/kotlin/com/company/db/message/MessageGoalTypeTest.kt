package com.company.db.message

import com.company.db.core.message.MessageGoalType
import com.company.objects.TypesObjects
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Тестирование функциональности класса MessageGoalType
 *
 * @author Nikita Gorodilov
 */
class MessageGoalTypeTest : Assert() {

    private lateinit var type: MessageGoalType

    /* Параметры */
    private val id = 1L
    private val code = TypesObjects.testMessageGoalCode1
    private val name = "name"
    private val isDeleted = false

    @Before
    fun setup() {
        type = MessageGoalType(
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
}