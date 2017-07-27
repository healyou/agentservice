package com.company.db.core.agent

import com.company.AbstractServiceTest
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Тестирование функциональности сервиса работы с Agent
 *
 * @author Nikita Gorodilov
 */
class AgentServiceTest: AbstractServiceTest() {

    @Autowired
    private lateinit var service: AgentService
    @Autowired
    private lateinit var typeService: AgentTypeService

    private var id: Long? = null
    private val masId = "masId"
    private val name = "name"
    private lateinit var type: AgentType
    private val createDate = Date(System.currentTimeMillis())
    private val isDeleted = false

    @Before
    fun setup() {
        type = typeService.get(AgentType.Code.WORKER)
        id = service.create(Agent(
                null,
                masId,
                name,
                type,
                createDate,
                isDeleted
        ))
    }

    @Test
    fun testGetCreateAgent() {
        val agent = service.get(id!!)

        assertEquals(id, agent.id!!)
        assertEquals(masId, agent.masId)
        assertEquals(name, agent.name)
        assertEquals(type.code, agent.type.code)
        assertEquals(createDate.time, agent.createDate.time)
        assertEquals(isDeleted, agent.isDeleted)
    }

    @Test
    fun testUpdateAgent() {
        var agent = service.get(id!!)

        /* новые значения */
        val newMasId = "newMasId"
        val newName = "newName"
        val newType = typeService.get(AgentType.Code.SERVER)
        val newCreateDate = Date(System.currentTimeMillis())
        val newIsDeleted = false

        agent.masId = newMasId
        agent.type = newType
        agent.name = newName
        agent.createDate = newCreateDate
        agent.isDeleted = newIsDeleted

        /* обновление записи в бд */
        val updateId = service.update(agent)
        agent = service.get(id!!)

        /* проверка результатов */
        assertEquals(updateId, id)
        assertEquals(id, agent.id!!)
        assertEquals(newMasId, agent.masId)
        assertEquals(newName, agent.name)
        assertEquals(newType.code, agent.type.code)
        assertEquals(newCreateDate.time, agent.createDate.time)
        assertEquals(newIsDeleted, agent.isDeleted)
    }

    @Test(expected = EmptyResultDataAccessException::class)
    fun testDeleteAgent() {
        /* проверяем что в бд есть агент */
        val agent = service.get(id!!)
        assertNotNull(agent)

        /* удаляем агента */
        service.delete(id!!)

        /* проверяем, что в бд нет агента -> EmptyResultDataAccessException*/
        service.get(id!!)
    }
}