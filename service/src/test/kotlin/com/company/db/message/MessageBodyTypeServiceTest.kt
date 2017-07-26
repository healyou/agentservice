package com.company.db.message

import com.company.AbstractServiceTest
import com.company.db.core.message.MessageBodyType
import com.company.db.core.message.MessageBodyTypeService
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

    @Test
    fun testGetAllTypes() {
        val types = service.get()

        assertEquals(1, types.size)
    }

    @Test
    fun getType() {
        val jsonType = service.get(MessageBodyType.Code.JSON)

        assertEquals(MessageBodyType.Code.JSON, jsonType.code)
    }
}