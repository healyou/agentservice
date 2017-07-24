package com.company.db.core.agent

import com.company.AbstractServiceTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

/**
 * @author Nikita Gorodilov
 */
open class AgentTypeServiceTest: AbstractServiceTest() {

    @Autowired
    private lateinit var service: AgentTypeService

    @Test
    fun testGetAllTypes() {
        val types = service.get()

        assertEquals(2, types.size)
    }

    @Test
    fun getType() {
        val workerType = service.get(AgentType.Code.WORKER)
        val serverType = service.get(AgentType.Code.WORKER)

        assertEquals(AgentType.Code.WORKER, workerType.code)
        assertEquals(AgentType.Code.WORKER, serverType.code)
    }
}