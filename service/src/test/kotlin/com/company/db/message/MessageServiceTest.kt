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
 *      +тестирование сервиса MessageRecipient класса
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

    /* сервис работы с получателями сообщения */
    @Autowired
    private lateinit var messageRecipientService: MessageRecipientService

    /* идентификатор создаваемого сообщения */
    private var id: Long? = null
    private lateinit var sender: Agent
    private lateinit var recipients: List<MessageRecipient>
    private lateinit var goalType: MessageGoalType
    private lateinit var messageType: MessageType
    private lateinit var bodyType: MessageBodyType
    private val createDate = Date(System.currentTimeMillis())
    private val body = "{}"

    @Before
    fun setup() {
        val message = createMessage()

        /* сохранение получателей - отдельно, тк нужен id сообщения при сохранении*/
        recipients = createRecipients(message)
        message.recipients = recipients
        /* при сохранениии получатели пересоздаются */
        messageService.update(message)

        goalType = message.goalType
        messageType = message.type
        bodyType = message.bodyType
        id = message.id
    }

    /* Тест получения созданного сообщения в бд */
    @Test
    fun testGetCreateMessage() {
        val message = messageService.get(id!!)

        /* проверка всех значений создания сообщения */
        assertEquals(id, message.id!!)
        assertEquals(sender.id!!, message.sender.id!!)
        assertEquals(recipients.size, message.recipients.size)
        recipients.forEachIndexed { index, recipient ->
            assertEquals(recipient.recipient.id, message.recipients[index].recipient.id)
        }
        assertEquals(goalType.code, message.goalType.code)
        assertEquals(messageType.code, message.type.code)
        assertEquals(bodyType.code, message.bodyType.code)
        assertEquals(body, message.body)
        assertNotNull(message.createDate)
    }

    /* Тест обновления сообщения в бд */
    @Test
    fun testUpdateMessage() {
        var message = messageService.get(id!!)

        /* новые значения */
        val newSender = createSender()
        val newRecipients = createRecipients(message)
        val newGoalType = goalTypeService.get(MessageGoalType.Code.TASK_DECISION)
        val newMessageType = messageTypeService.get(MessageType.Code.TASK_SOLUTION_ANSWER)
        val newCreateDate = Date(System.currentTimeMillis())
        val newBodyType = messageBodyTypeService.get(MessageBodyType.Code.JSON)
        val newBody = "{ 'a': 1}"

        /* присвоение новых значений */
        message.sender = newSender
        message.recipients = newRecipients
        message.goalType = newGoalType
        message.type = newMessageType
        message.createDate = newCreateDate
        message.bodyType = newBodyType
        message.body = newBody

        /* обновление записи в бд */
        val updateId = messageService.update(message)
        message = messageService.get(id!!)

        /* проверка результатов */
        assertEquals(updateId, id)

        /* проверка обновляемых параметров */
        assertEquals(id, message.id!!)
        assertEquals(newSender.id!!, message.sender.id!!)
        assertEquals(newRecipients.size, message.recipients.size)
        newRecipients.forEachIndexed { index, agent ->
            assertEquals(agent.recipient.id, message.recipients[index].recipient.id)
        }
        assertEquals(newGoalType.code, message.goalType.code)
        assertEquals(newBodyType.code, message.bodyType.code)
        assertEquals(newBody, message.body)

        /* проверка не обновляемых параметров */
        assertNotNull(message.type)
        assertNotNull(message.createDate)
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

    /* Проверка создания MessageRecipient */
    @Test
    fun testMessageRecipientGetData() {
        val message = messageService.get(id!!)

        message.recipients.forEach {
            assertNotNull(it.id)
            assertNotNull(it.recipient)
            assertNotNull(it.viewedDate)
        }
    }

    /* Проверка обновления MessageRecipient */
    @Test(expected = EmptyResultDataAccessException::class)
    fun testMessageRecipientDeleteById() {
        val recipient = messageService.get(id!!).recipients[0]

        messageRecipientService.delete(recipient)
        messageRecipientService.getById(recipient.id!!)
    }

    /* Тест удаления всех получателей сообщения*/
    @Test
    fun testMessageRecipientDeleteByMessage() {
        val message = messageService.get(id!!)

        messageRecipientService.delete(message)
        assertEquals(0, messageRecipientService.get(message).size)
    }

    /* Тест поиска получателей сообщения по id */
    @Test
    fun testGetMessageRecipientById() {
        val recipient = messageService.get(id!!).recipients[0]

        val searchRecipient = messageRecipientService.getById(recipient.id!!)
        assertNotNull(searchRecipient)
        assertEquals(recipient.id, searchRecipient.id)
        assertEquals(recipient.recipient.id, searchRecipient.recipient.id)
    }

    /* Тест поиска получателя сообщения по id получателя*/
    @Test
    fun testGetMessageRecipientByRecipientId() {
        val recipient = messageService.get(id!!).recipients[0]

        val searchRecipients = messageRecipientService.getByRecipientId(recipient.recipient.id!!)
        assertNotNull(searchRecipients)
        assertTrue { searchRecipients.isNotEmpty() }
    }

    @Test
    fun testGetMessageRecipientByMessage() {
        val message = messageService.get(id!!)

        assertNotNull(messageRecipientService.get(message))
        assertEquals(message.recipients.size, messageRecipientService.get(message).size)
    }

    /* СОЗДАНИЕ ОБЪЕКТОВ */

    /* Создание сообщения */
    private fun createMessage(): Message {
        val goalType = goalTypeService.get(MessageGoalType.Code.TASK_DECISION)
        val messageType = messageTypeService.get(MessageType.Code.SEARCH_SOLUTION)
        val bodyType = messageBodyTypeService.get(MessageBodyType.Code.JSON)
        sender = createSender()

        val id = messageService.create(Message(
                null,
                sender,
                arrayListOf(),
                goalType,
                messageType,
                createDate,
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
    private fun createRecipients(message: Message): List<MessageRecipient> {
        val agentType = agentTypeService.get(AgentType.Code.WORKER)
        return arrayListOf<MessageRecipient>(
                createMessageRecipient(message, agentType),
                createMessageRecipient(message, agentType),
                createMessageRecipient(message, agentType),
                createMessageRecipient(message, agentType),
                createMessageRecipient(message, agentType)
        )
    }

    private fun createMessageRecipient(message: Message, agentType: AgentType): MessageRecipient {
        val id = messageRecipientService.create(
                message.id!!,
                MessageRecipient(
                        null,
                        createAgent(agentType),
                        null
                )
        )
        return messageRecipientService.getById(id)
    }

    /* создание агента */
    private fun createAgent(agentType: AgentType): Agent {
        val id = agentService.create(Agent(
                null,
                UUID.randomUUID().toString(),
                "name",
                agentType,
                Date(System.currentTimeMillis()),
                false
        ))
        return agentService.get(id)
    }
}