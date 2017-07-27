package com.company.db.message

import com.company.AbstractServiceTest
import com.company.db.core.message.MessageBodyType
import com.company.db.core.message.MessageBodyTypeService
import com.company.db.core.message.MessageGoalType
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

/**
 * Тестирование функциональности сервиса работы с MessageBodyType
 *
 * @author Nikita Gorodilov
 */
class MessageBodyTypeServiceTest: AbstractServiceTest() {

    @Autowired
    private lateinit var service: MessageBodyTypeService

    /* количество значений в базе данных */
    private val MESSAGE_BODY_TYPES_SIZE = MessageBodyType.Code.values().size

    @Test
    fun testGetAllTypes() {
        val types = service.get()

        assertEquals(MESSAGE_BODY_TYPES_SIZE, types.size)
    }

    @Test
    fun getType() {
        val jsonType = service.get(MessageBodyType.Code.JSON)

        assertEquals(MessageBodyType.Code.JSON, jsonType.code)
    }
}