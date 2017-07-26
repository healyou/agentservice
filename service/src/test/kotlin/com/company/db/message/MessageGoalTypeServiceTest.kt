package com.company.db.message

import com.company.AbstractServiceTest
import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageGoalTypeService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

/**
 * Тестирование функциональности работы сервиса MessageGoalService
 *
 * @author Nikita Gorodilov
 */
class MessageGoalTypeServiceTest : AbstractServiceTest() {

    @Autowired
    private lateinit var service: MessageGoalTypeService

    @Test
    fun testGetAllTypes() {
        val types = service.get()

        assertEquals(1, types.size)
    }

    @Test
    fun getType() {
        val jsonType = service.get(MessageGoalType.Code.TASK_DECISION)

        assertEquals(MessageGoalType.Code.TASK_DECISION, jsonType.code)
    }
}