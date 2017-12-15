package com.company.db.core.agent

import com.company.objects.TypesObjects
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Тестирование функциональности класса объекта AgentType
 *      Здесь нет привязки классов к базе данных
 *      Проверяется только работа самого класса
 *
 * @author Nikita Gorodilov
 */
class AgentTypeTest: Assert() {

    private lateinit var agentType: AgentType

    /* параметры */
    private val id = 1L
    private val code: String = TypesObjects.testAgentCode1
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
}