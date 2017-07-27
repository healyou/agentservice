package com.company.db.core.agent

import com.company.AbstractServiceTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

/**
 * Тестирование функциональности сервиса работы с AgentType
 *
 * @author Nikita Gorodilov
 */
open class AgentTypeServiceTest: AbstractServiceTest() {

    @Autowired
    private lateinit var service: AgentTypeService

    /* количество значений в базе данных */
    private val AGENT_TYPES_SIZE = AgentType.Code.values().size

    @Test
    fun testGetAllTypes() {
        val types = service.get()

        assertEquals(AGENT_TYPES_SIZE, types.size)
    }

    @Test
    fun getType() {
        val workerType = service.get(AgentType.Code.WORKER)
        val serverType = service.get(AgentType.Code.WORKER)

        assertEquals(AgentType.Code.WORKER, workerType.code)
        assertEquals(AgentType.Code.WORKER, serverType.code)
    }
}