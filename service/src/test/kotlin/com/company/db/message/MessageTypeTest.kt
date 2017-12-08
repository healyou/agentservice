package com.company.db.message

import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageType
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Тестирование функциональности класса MessageType
 *      Здесь нет привязки классов к базе данных
 *      Проверяется только работа самого класса
 * @author Nikita Gorodilov
 */
class MessageTypeTest : Assert() {

    private lateinit var type: MessageType

    /* Параметры */
    private val id = 1L
    private val code = MessageType.Code.SOLUTION_ANSWER
    private val name = "name"
    private val messageOrder = 3L
    private val messageGoalType = MessageGoalType(
            1L,
            MessageGoalType.Code.TASK_DECISION,
            "name",
            false
    )
    private val isDeleted = false

    @Before
    fun setup() {
        type = MessageType(
                id,
                code,
                name,
                messageOrder,
                messageGoalType,
                isDeleted
        )
    }

    @Test
    fun testTypeGetData() {
        assertEquals(id, type.id)
        assertEquals(code, type.code)
        assertEquals(name, type.name)
        assertEquals(messageOrder, type.messageOrder)
        assertEquals(messageGoalType, type.messageGoalType)
        assertEquals(isDeleted, type.isDeleted)
    }
}