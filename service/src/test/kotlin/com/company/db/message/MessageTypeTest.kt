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
    private val messageOrder = 3L // todo поля, которые ненадо в базе менять - createDate и другие, что с ними делать
    /* для теста необязательно из бд считывать, тут проверим лишь общую работу класса */
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

    @Test
    fun testTypeSetDate() {
        /* новые значение */
        val newId = 2L
        val newCode = MessageType.Code.TASK_SOLUTION_ANSWER
        val newName = "newName"
        val newMessageOrder = 4L
        val newMessageGoalType = MessageGoalType(
                2L,
                MessageGoalType.Code.TASK_DECISION,
                "name2",
                true
        )
        val newIsDeleted = true

        type.id = newId
        type.code = newCode
        type.name = newName
        type.messageOrder = newMessageOrder
        type.messageGoalType = newMessageGoalType
        type.isDeleted = newIsDeleted

        assertEquals(newId, type.id!!)
        assertEquals(newCode, type.code)
        assertEquals(newName, type.name)
        assertEquals(newMessageOrder, type.messageOrder)
        assertEquals(newMessageGoalType, type.messageGoalType)
        assertEquals(newIsDeleted, type.isDeleted)
    }
}