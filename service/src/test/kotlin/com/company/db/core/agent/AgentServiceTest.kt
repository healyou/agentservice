package com.company.db.core.agent

import com.company.AbstractServiceTest
import com.company.db.core.sc.AgentSC
import com.company.objects.TypesObjects
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.UncategorizedSQLException
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
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
        type = typeService.getByCode(TypesObjects.testAgentCode1)
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

    /* Получение агентов с типом testAgentCode1 */
    @Test
    fun testGetAgentsByTypeWorker() {
        /* Cоздание агентов testAgentCode1 */
        val agentTypeCode1 = TypesObjects.testAgentCode1
        val workerType = typeService.getByCode(agentTypeCode1)
        createTestAgent(workerType)
        createTestAgent(workerType)

        /* Получаем список агентов */
        val sc = AgentSC()
        sc.type = agentTypeCode1
        val agents = service.get(sc)

        /* Только агенты с типом testAgentCode1 */
        assertTrue {
            agents.filter {
                it.type.code != sc.type
            }.isEmpty() && !agents.isEmpty()
        }
    }

    /* Получение агентов с типом testAgentCode2 */
    @Test
    fun testGetAgentsByTypeServer() {
        /* Cоздание агентов testAgentCode2 */
        val agentTypeCode2 = TypesObjects.testAgentCode2
        val serverType = typeService.getByCode(agentTypeCode2)
        createTestAgent(serverType)
        createTestAgent(serverType)

        /* Получаем список агентов */
        val sc = AgentSC()
        sc.type = agentTypeCode2
        val agents = service.get(sc)

        /* Только агенты с типом AgentType.Code.SERVER */
        assertTrue {
            agents.filter {
                it.type.code != sc.type
            }.isEmpty() && !agents.isEmpty()
        }
    }

    /* Получение всех удалённых и не удалённых агентов */
    @Test
    fun testGetIsDeletedAgents() {
        /* Удаляем агента */
        val testTypeCode1 = typeService.getByCode(TypesObjects.testAgentCode1)
        val agent = createTestAgent(testTypeCode1)
        agent.isDeleted = true
        service.update(agent)

        /* Не удалённые агенты */
        createTestAgent(testTypeCode1)
        createTestAgent(testTypeCode1)

        val sc = AgentSC()

        /* Получаем удалённых агентов */
        sc.type = testTypeCode1.code
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
        val newType = typeService.getByCode(TypesObjects.testAgentCode2)
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
        val agentType = typeService.getByCode(TypesObjects.testAgentCode1)
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

    /**
     * Проверка существования агента в бд
     */
    @Test
    fun testIsExistsAgent() {
        val agentType = typeService.getByCode(TypesObjects.testAgentCode1)
        val masId = UUID.randomUUID().toString()

        assertFalse(service.isExistsAgent(masId))
        createTestAgentByMasId(agentType, masId)
        assertTrue(service.isExistsAgent(masId))
    }

    private fun createTestAgent(type: AgentType): Agent {
        return createTestAgent(type, "name")
    }

    private fun createTestAgent(type: AgentType, name: String): Agent {
        return createTestAgent(type, name, UUID.randomUUID().toString())
    }

    private fun createTestAgentByMasId(type: AgentType, masId: String): Agent {
        return createTestAgent(type, "name", masId)
    }

    private fun createTestAgent(type: AgentType, name: String, masId: String): Agent {
        val id = service.create(Agent(
                null,
                masId,
                name,
                type,
                Date(System.currentTimeMillis()),
                false
        ))
        return service.get(id)
    }
}