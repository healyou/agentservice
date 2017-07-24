package com.company.db.core.agent

import org.junit.Assert
import org.junit.Before;
import org.junit.Test
import java.util.*

/**
 * Тестирование фукнциональности класса объекта Agent
 *
 * @author Nikita Gorodilov
 */
class AgentTest: Assert() {

    private lateinit var agent: Agent

    /* параметры агента */
    private val ID = 1L
    private val MAS_ID = "masId"
    private val NAME = "name"
    private lateinit var type: AgentType
    private val CREATE_DATE = Date(System.currentTimeMillis())
    private val IS_DELETED = false

    @Before
    fun setup() {
        type = AgentType(
                1L,
                AgentType.Code.SERVER,
                "typeName",
                false
        )
        agent = Agent(
                ID,
                MAS_ID,
                NAME,
                type,
                CREATE_DATE,
                IS_DELETED
        )
    }

    @Test
    fun testAgentGetData() {
        assertEquals(ID, agent.id)
        assertEquals(MAS_ID, agent.masId)
        assertEquals(NAME, agent.name)
        assertEquals(type, agent.type)
        assertEquals(CREATE_DATE, agent.createDate)
        assertEquals(IS_DELETED, agent.isDeleted)
    }

    @Test
    fun testAgentSetData() {
        /* новые значение */
        val newId = 1L
        val newMasId = "masId"
        val newName = "name"
        val newType: AgentType = AgentType(
                1L,
                AgentType.Code.SERVER,
                "newTypeName",
                false
        )
        val newCreateDate = Date(System.currentTimeMillis())
        val newIsDeleted = false

        agent.id = newId
        agent.masId = newMasId
        agent.type = newType
        agent.name = newName
        agent.createDate = newCreateDate
        agent.isDeleted = newIsDeleted

        assertEquals(newId, agent.id)
        assertEquals(newMasId, agent.masId)
        assertEquals(newName, agent.name)
        assertEquals(newType, agent.type)
        assertEquals(newCreateDate, agent.createDate)
        assertEquals(newIsDeleted, agent.isDeleted)
    }
}