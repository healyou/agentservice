package com.company.db.core.agent

import com.company.AbstractServiceTest
import com.company.objects.TypesObjects
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Тестирование функциональности сервиса работы с AgentType
 *
 * @author Nikita Gorodilov
 */
open class AgentTypeServiceTest: AbstractServiceTest() {

    @Autowired
    private lateinit var service: AgentTypeService

    @Test
    fun testNotEmptyTypes() {
        val types = service.get()
        assertTrue(types.isNotEmpty())
    }

    @Test
    fun getType() {
        val agentType1 = service.getByCode(TypesObjects.testAgentCode1)
        val agentType2 = service.getByCode(TypesObjects.testAgentCode2)

        assertEquals(TypesObjects.testAgentCode1, agentType1.code)
        assertEquals(TypesObjects.testAgentCode2, agentType2.code)
    }
}