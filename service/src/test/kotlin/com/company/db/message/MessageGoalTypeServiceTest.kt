package com.company.db.message

import com.company.AbstractServiceTest
import com.company.db.core.message.MessageGoalTypeService
import com.company.objects.TypesObjects
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Тестирование функциональности работы сервиса MessageGoalService
 *
 * @author Nikita Gorodilov
 */
class MessageGoalTypeServiceTest : AbstractServiceTest() {

    @Autowired
    private lateinit var service: MessageGoalTypeService

    @Test
    fun testNotEmptyTypes() {
        val types = service.get()
        assertTrue(types.isNotEmpty())
    }

    @Test
    fun getType() {
        val testMessageGoalCode1 = TypesObjects.testMessageGoalCode1
        val testMessageGoalType1 = service.getByCode(testMessageGoalCode1)

        assertEquals(testMessageGoalCode1, testMessageGoalType1.code)
    }
}