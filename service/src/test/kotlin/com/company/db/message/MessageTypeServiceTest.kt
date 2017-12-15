package com.company.db.message

import com.company.AbstractServiceTest
import com.company.db.core.message.MessageTypeService
import com.company.objects.TypesObjects
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Тестирование функциональности сервиса работы с MessageType
 *
 * @author Nikita Gorodilov
 */
class MessageTypeServiceTest : AbstractServiceTest() {

    @Autowired
    private lateinit var messageTypeService: MessageTypeService

    @Test
    fun testGetAllTypes() {
        val testMessageGoalType1 = messageTypeService.getByCode(TypesObjects.testMessageCode1).goalType
        val types = messageTypeService.get(testMessageGoalType1)

        assertTrue(types.isNotEmpty())
    }

    @Test
    fun getType() {
        val type = messageTypeService.getByCode(TypesObjects.testMessageCode1)

        assertEquals(TypesObjects.testMessageCode1, type.code)
    }

    @Test
    fun getTypes() {
        val types = messageTypeService.get()
        assertTrue(types.isNotEmpty())
    }
}