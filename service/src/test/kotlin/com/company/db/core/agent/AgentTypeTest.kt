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
    private val ID = 1L
    private val CODE: AgentType.Code = AgentType.Code.SERVER
    private val TYPE_NAME = "typeName"
    private val IS_DELETED = false

    @Before
    fun setup() {
        agentType = AgentType(
                ID,
                CODE,
                TYPE_NAME,
                IS_DELETED
        )
    }

    @Test
    fun testAgentTypeGetData() {
        assertEquals(ID, agentType.id)
        assertEquals(CODE, agentType.code)
        assertEquals(TYPE_NAME, agentType.name)
        assertEquals(IS_DELETED, agentType.isDeleted)
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

        assertEquals(newId, agentType.id)
        assertEquals(newCode, agentType.code)
        assertEquals(newName, agentType.name)
        assertEquals(newIsDeleted, agentType.isDeleted)
    }
}