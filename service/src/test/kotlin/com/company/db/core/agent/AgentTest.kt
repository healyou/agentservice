package com.company.db.core.agent

import org.junit.Assert
import org.junit.Before;
import org.junit.Test
import java.util.*

/**
 * Тестирование фукнциональности класса объекта Agent
 *      Здесь нет привязки классов к базе данных
 *      Проверяется только работа самого класса
 *
 * @author Nikita Gorodilov
 */
class AgentTest: Assert() {

    private lateinit var agent: Agent

    /* параметры агента */
    private val id = 1L
    private val masId = "masId"
    private val name = "name"
    private lateinit var type: AgentType
    private val createDate = Date(System.currentTimeMillis())
    private val isDeleted = false

    @Before
    fun setup() {
        type = AgentType(
                1L,
                AgentType.Code.SERVER,
                "typeName",
                false
        )
        agent = Agent(
                id,
                masId,
                name,
                type,
                createDate,
                isDeleted
        )
    }

    @Test
    fun testAgentGetData() {
        assertEquals(id, agent.id)
        assertEquals(masId, agent.masId)
        assertEquals(name, agent.name)
        assertEquals(type, agent.type)
        assertEquals(createDate, agent.createDate)
        assertEquals(isDeleted, agent.isDeleted)
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

        assertEquals(newId, agent.id!!)
        assertEquals(newMasId, agent.masId)
        assertEquals(newName, agent.name)
        assertEquals(newType, agent.type)
        assertEquals(newCreateDate, agent.createDate)
        assertEquals(newIsDeleted, agent.isDeleted)
    }
}