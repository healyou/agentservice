package com.company.db.core.agent

import com.company.AbstractServiceTest
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

/**
 * @author Nikita Gorodilov
 */
class AgentServiceTest: AbstractServiceTest() {

    @Autowired
    private lateinit var service: AgentService

    @Before
    fun setup() {
        // todo ещё 3 метода дотестить
    }

    @Test
    fun testGetAll() {
        val agents = service.get()

        assertEquals(3, agents.size)
    }

    @Test
    fun getOne() {
        val agent = service.get(1L)

        assertEquals(1L, agent.id)
    }
}