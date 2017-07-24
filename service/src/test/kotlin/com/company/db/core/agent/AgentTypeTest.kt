package com.company.db.core.agent

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Тестирование фукнциональности класса объекта AgentType
 *
 * @author Nikita Gorodilov
 */
class AgentTypeTest: Assert() {

    private lateinit var agentType: AgentType

    /* параметры */
    private val id = 1L
    private val code: AgentType.Code = AgentType.Code.SERVER
    private val typeName = "typeName"
    private val isDeleted = false

    @Before
    fun setup() {
        agentType = AgentType(
                id,
                code,
                typeName,
                isDeleted
        )
    }

    @Test
    fun testAgentTypeGetData() {
        assertEquals(id, agentType.id)
        assertEquals(code, agentType.code)
        assertEquals(typeName, agentType.name)
        assertEquals(isDeleted, agentType.isDeleted)
    }

    @Test
    fun testAgentTypeSetData() {
        /* новые значение */
        val newId = 1L
        val newCode = AgentType.Code.WORKER
        val newName = "name"
        val newIsDeleted = true

        agentType.id = newId
        agentType.code = newCode
        agentType.name = newName
        agentType.isDeleted = newIsDeleted

        assertEquals(newId, agentType.id!!)
        assertEquals(newCode, agentType.code)
        assertEquals(newName, agentType.name)
        assertEquals(newIsDeleted, agentType.isDeleted)
    }
}