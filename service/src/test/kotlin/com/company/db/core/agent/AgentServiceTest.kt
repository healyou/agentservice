package com.company.db.core.agent

import com.company.AbstractServiceTest
import com.company.db.core.sc.AgentSC
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.UncategorizedSQLException
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

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
    private val masId = UUID.randomUUID().toString()
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
    fun testGetCreateAgentById() {
        val agent = service.get(id!!)

        assertEquals(id, agent.id!!)
        assertEquals(masId, agent.masId)
        assertEquals(name, agent.name)
        assertEquals(type.code, agent.type.code)
        assertEquals(createDate.time, agent.createDate.time)
        assertEquals(isDeleted, agent.isDeleted)
    }

    @Test
    fun testGetCreateAgentByMasId() {
        val agent = service.getByMasId(masId)

        assertEquals(id, agent.id!!)
        assertEquals(masId, agent.masId)
        assertEquals(name, agent.name)
        assertEquals(type.code, agent.type.code)
        assertEquals(createDate.time, agent.createDate.time)
        assertEquals(isDeleted, agent.isDeleted)
    }

    /* Получение агентов с типом AgentType.Code.WORKER */
    @Test
    fun testGetAgentsByTypeWorker() {
        /* Cоздание агентов AgentType.Code.WORKER */
        val agentTypeWorker = AgentType.Code.WORKER
        val workerType = typeService.get(agentTypeWorker)
        createTestAgent(workerType)
        createTestAgent(workerType)

        /* Получаем список агентов */
        val sc = AgentSC()
        sc.type = agentTypeWorker.code
        val agents = service.get(sc)

        /* Только агенты с типом AgentType.Code.WORKER */
        assertTrue {
            agents.filter {
                it.type.code.code != sc.type
            }.isEmpty() && !agents.isEmpty()
        }
    }

    /* Получение агентов с типом AgentType.Code.SERVER */
    @Test
    fun testGetAgentsByTypeServer() {
        /* Cоздание агентов AgentType.Code.SERVER */
        val agentTypeServer = AgentType.Code.SERVER
        val serverType = typeService.get(agentTypeServer)
        createTestAgent(serverType)
        createTestAgent(serverType)

        /* Получаем список агентов */
        val sc = AgentSC()
        sc.type = agentTypeServer.code
        val agents = service.get(sc)

        /* Только агенты с типом AgentType.Code.SERVER */
        assertTrue {
            agents.filter {
                it.type.code.code != sc.type
            }.isEmpty() && !agents.isEmpty()
        }
    }

    /* Получение всех удалённых и не удалённых агентов */
    @Test
    fun testGetIsDeletedAgents() {
        /* Удаляем агента */
        val serverType = typeService.get(AgentType.Code.SERVER)
        val agent = createTestAgent(serverType)
        agent.isDeleted = true
        service.update(agent)

        /* Не удалённые агенты */
        createTestAgent(serverType)
        createTestAgent(serverType)

        val sc = AgentSC()

        /* Получаем удалённых агентов */
        sc.isDeleted = true
        var agents = service.get(sc)
        /* Только удалённые агенты */
        assertTrue {
            agents.filter {
                it.isDeleted != sc.isDeleted
            }.isEmpty() && !agents.isEmpty()
        }

        /* Получаем не удалённых агентов */
        sc.isDeleted = false
        agents = service.get(sc)
        /* Только не удалённые агенты */
        assertTrue {
            agents.filter {
                it.isDeleted != sc.isDeleted
            }.isEmpty() && !agents.isEmpty()
        }
    }

    @Test(expected = EmptyResultDataAccessException::class)
    fun testErrorGetAgentByMasId() {
        service.getByMasId(UUID.randomUUID().toString())
    }

    /* нельзя создать агента с одинаковым masId */
    @Test(expected = UncategorizedSQLException::class)
    fun testErrorCreateTwoAgentWithoutUniqueMasId() {
        val uniqueMasId = "uniqueMasId"
        service.create(Agent(
                null,
                uniqueMasId,
                name,
                type,
                createDate,
                isDeleted
        ))
        /* второго агента создать не должно */
        service.create(Agent(
                null,
                uniqueMasId,
                name,
                type,
                createDate,
                isDeleted
        ))
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

    /* Получение агента по имени без привязки к заглавным и маленьким буквам */
    @Test
    fun getAgentByMasId() {
        val agentType = typeService.get(AgentType.Code.values()[0])
        val name = UUID.randomUUID().toString()
        val agents = arrayListOf(
                createTestAgent(agentType, name.toLowerCase()),
                createTestAgent(agentType, name.toUpperCase()),
                createTestAgent(agentType, name.toLowerCase())
        )

        val sc = AgentSC()
        sc.name = name
        val getAgents = service.get(sc)

        assertEquals(agents.size, getAgents.size)
        assertTrue {
            agents.all { agent ->
                getAgents.any { getAgent ->
                    agent.id == getAgent.id
                }
            }
        }
    }

    private fun createTestAgent(type: AgentType): Agent {
        return createTestAgent(type, "name")
    }

    private fun createTestAgent(type: AgentType, name: String): Agent {
        val id = service.create(Agent(
                null,
                UUID.randomUUID().toString(),
                name,
                type,
                Date(System.currentTimeMillis()),
                false
        ))
        return service.get(id)
    }
}