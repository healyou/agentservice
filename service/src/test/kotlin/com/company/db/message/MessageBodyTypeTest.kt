package com.company.db.message

import com.company.db.core.message.MessageBodyType
import com.company.objects.TypesObjects
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Тестирование функциональности класса MessageBodyType
 *      Здесь нет привязки классов к базе данных
 *      Проверяется только работа самого класса
 *
 * @author Nikita Gorodilov
 */
class MessageBodyTypeTest: Assert() {

    private lateinit var type: MessageBodyType

    /* Параметры */
    private val id = 1L
    private val code = TypesObjects.testMessageBodyCode1
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
}