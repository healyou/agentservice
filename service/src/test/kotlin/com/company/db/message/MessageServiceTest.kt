package com.company.db.message

import com.company.AbstractServiceTest
import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentService
import com.company.db.core.agent.AgentType
import com.company.db.core.agent.AgentTypeService
import com.company.db.core.message.*
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Тестирование функциональности сервиса работы с Message
 *      Включает множество других классов для работы
 *      Поэтому при ошибке лучше вначале искать её в других сервисах
 *
 * @author Nikita Gorodilov
 */
class MessageServiceTest : AbstractServiceTest() {

    /* сервис работы с сообщениями */
    @Autowired
    private lateinit var messageService: MessageService

    /* сервис работы с агентами */
    @Autowired
    private lateinit var agentService: AgentService

    /* сервис работы с типами агентов */
    @Autowired
    private lateinit var agentTypeService: AgentTypeService

    /* сервис работы с целью сообщения */
    @Autowired
    private lateinit var goalTypeService: MessageGoalTypeService

    /* сервис работы с типом сообщения */
    @Autowired
    private lateinit var messageTypeService: MessageTypeService

    /* сервис работы с типом тела сообщения */
    @Autowired
    private lateinit var messageBodyTypeService: MessageBodyTypeService

    /* идентификатор создаваемого сообщения */
    private var id: Long? = null
    private lateinit var sender: Agent
    private lateinit var recipients: List<Agent>
    private lateinit var goalType: MessageGoalType
    private lateinit var messageType: MessageType
    private lateinit var bodyType: MessageBodyType
    private val createDate = Date(System.currentTimeMillis())
    private val viewedDate: Date? = null
    private val isViewed = false
    private val body = "{}"

    @Before
    fun setup() {
        val message = createMessage()

        goalType = message.goalType
        messageType = message.type
        bodyType = message.bodyType
        id = message.id
    }

    /* Тест получения созданного сообщения в бд */
    @Test
    fun testGetCreateMessage() {
        val message = messageService.get(id!!)

        assertEquals(id, message.id!!)
        assertEquals(sender.id!!, message.sender.id!!)
        //assertEquals(recipients.size, message.recipients.size)
        assertEquals(goalType.code, message.goalType.code)
        assertEquals(messageType.code, message.type.code)
        //assertEquals(createDate, message.createDate)
        //assertEquals(viewedDate, message.viewedDate)
        //assertEquals(isViewed, message.isViewed)
        assertEquals(bodyType.code, message.bodyType.code)
        assertEquals(body, message.body)
    }

    /* Тест обновления сообщения в бд */
    @Test
    fun testUpdateMessage() {
        var message = messageService.get(id!!)

        /* новые значения */
        val newSender = createSender()
        val newRecipients = createRecipients()
        val newGoalType = goalTypeService.get(MessageGoalType.Code.TASK_DECISION)
        val newMessageType = messageTypeService.get(MessageType.Code.TASK_SOLUTION_ANSWER)
        val newCreateDate = Date(System.currentTimeMillis())
        val newViewedDate = Date(System.currentTimeMillis())
        val newIsViewed = true
        val newBodyType = messageBodyTypeService.get(MessageBodyType.Code.JSON)
        val newBody = "{ 'a': 1}"

        /* присвоение новых значений */
        message.sender = newSender
        message.recipients = newRecipients
        message.goalType = newGoalType
        message.type = newMessageType
        message.createDate = newCreateDate
        message.viewedDate = newViewedDate
        message.isViewed = newIsViewed
        message.bodyType = newBodyType
        message.body = newBody

        /* обновление записи в бд */
        val updateId = messageService.update(message)
        message = messageService.get(id!!)

        /* проверка результатов */
        assertEquals(updateId, id)

        assertEquals(id, message.id!!)
        //assertEquals(newSender.id!!, message.sender.id!!)
        //assertEquals(newRecipients.size, message.recipients.size)
        assertEquals(newGoalType.code, message.goalType.code)
        //assertEquals(newMessageType.code, message.type.code)
        //assertEquals(newCreateDate, message.createDate)
        assertEquals(newViewedDate, message.viewedDate)
        assertEquals(newIsViewed, message.isViewed)
        assertEquals(newBodyType.code, message.bodyType.code)
        assertEquals(newBody, message.body)
    }

    /* Тест получение всех сообщений из бд */
    @Test
    fun testGetAllMessages() {
        createMessage()

        /* 1 сообщение создано в before и одно создаём тут - и того 2 сообщения */
        assertTrue { messageService.get().size >= 2 }
    }

    /* Тест удаления сообщения */
    @Test(expected = EmptyResultDataAccessException::class)
    fun testDeleteMessage() {
        /* проверяем что в бд есть агент */
        val message = messageService.get(id!!)
        assertNotNull(message)

        /* удаляем сообщение */
        messageService.delete(id!!)

        /* проверяем, что в бд нет сообщения -> EmptyResultDataAccessException*/
        messageService.get(id!!)
    }

    /* СОЗДАНИЕ ОБЪЕКТОВ */

    /* id для следующего агента */
    private var _id = 1L
    private var nextId: Long = _id
        get() = _id++

    /* Создание сообщения */
    private fun createMessage(): Message {
        val goalType = goalTypeService.get(MessageGoalType.Code.TASK_DECISION)
        val messageType = messageTypeService.get(MessageType.Code.SEARCH_SOLUTION)
        val bodyType = messageBodyTypeService.get(MessageBodyType.Code.JSON)
        sender = createSender()
        recipients = createRecipients()

        val id = messageService.create(Message(
                null,
                sender,
                recipients,
                goalType,
                messageType,
                createDate,
                viewedDate,
                isViewed,
                bodyType,
                body
        ))
        return messageService.get(id)
    }

    /* создание отправителя сообщения */
    private fun createSender(): Agent {
        val agentType = agentTypeService.get(AgentType.Code.WORKER)
        return createAgent(agentType)
    }

    /* создание получателей сообщения */
    private fun createRecipients(): List<Agent> {
        val agentType = agentTypeService.get(AgentType.Code.WORKER)
        return arrayListOf<Agent>(
                createAgent(agentType),
                createAgent(agentType),
                createAgent(agentType),
                createAgent(agentType),
                createAgent(agentType)
        )
    }

    /* создание агента */
    private fun createAgent(agentType: AgentType): Agent {
        val id = agentService.create(Agent(
                null,
                "masId",
                "name",
                agentType,
                Date(System.currentTimeMillis()),
                false
        ))
        return agentService.get(id)
    }
}