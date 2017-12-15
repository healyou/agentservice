package com.company.db.message

import com.company.AbstractServiceTest
import com.company.db.core.message.MessageBodyTypeService
import com.company.objects.TypesObjects
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Тестирование функциональности сервиса работы с MessageBodyType
 *
 * @author Nikita Gorodilov
 */
class MessageBodyTypeServiceTest: AbstractServiceTest() {

    @Autowired
    private lateinit var service: MessageBodyTypeService

    @Test
    fun testNotEmptyTypes() {
        val types = service.get()
        assertTrue(types.isNotEmpty())
    }

    @Test
    fun getType() {
        val testBodyType1 = service.getByCode(TypesObjects.testMessageBodyCode1)
        assertEquals(TypesObjects.testMessageBodyCode1, testBodyType1.code)
    }
}