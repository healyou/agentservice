package com.company.db.message

import com.company.AbstractServiceTest
import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageGoalTypeService
import com.company.db.core.message.MessageType
import com.company.db.core.message.MessageTypeService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

/**
 * Тестирование функциональности сервиса работы с MessageType
 *
 * @author Nikita Gorodilov
 */
class MessageTypeServiceTest : AbstractServiceTest() {

    @Autowired
    private lateinit var messageTypeService: MessageTypeService
    @Autowired
    private lateinit var messageGoalTypeService: MessageGoalTypeService

    /* количество значений в базе данных для messageType связанных с MessageGoalType.Code.TASK_DECISION*/
    private val MESSAGE_TYPES_ON_TASK_DECISION_SIZE = 4

    @Test
    fun testGetAllTypes() {
        val messageGoalType = messageGoalTypeService.get(MessageGoalType.Code.TASK_DECISION)
        val types = messageTypeService.get(messageGoalType)

        assertEquals(MESSAGE_TYPES_ON_TASK_DECISION_SIZE, types.size)
    }

    @Test
    fun getType() {
        val type = messageTypeService.get(MessageType.Code.TASK_SOLUTION_ANSWER)

        assertEquals(MessageType.Code.TASK_SOLUTION_ANSWER, type.code)
    }
}