package com.company.db.message

import com.company.AbstractServiceTest
import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentService
import com.company.db.core.agent.AgentType
import com.company.db.core.agent.AgentTypeService
import com.company.db.core.message.*
import com.company.db.core.sc.MessageSC
import com.company.objects.TypesObjects
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import java.util.*
import kotlin.test.*

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

        goalType = message.type.goalType
        messageType = message.type
        bodyType = message.bodyType
        id = message.id
    }

    /* Сохранение получателей в сообщении */
    @Test
    fun testSaveMessageWithRecipients() {
        val expectedMessageRecipients = createMessageRecipients(2)
        val message = createMessage(expectedMessageRecipients)
        val actualMessageRecipients = message.recipients

        expectedMessageRecipients.forEachIndexed { index, recipient ->
            assertEquals(recipient.recipient.id, actualMessageRecipients[index].recipient.id)
        }
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
        assertEquals(messageType.code, message.type.code)
        assertEquals(goalType.code, messageType.goalType.code)
        assertEquals(bodyType.code, message.bodyType.code)
        assertEquals(body, message.body)
        assertNotNull(message.createDate)
    }

    /* Тест получения просмотренных сообщений */
    @Test
    fun testGetIsViewedMessages() {
        /* Сообщение */
        val message = messageService.get(id!!)
        /* Получатель сообщения */
        val agentType = agentTypeService.getByCode(TypesObjects.testAgentCode1)
        val messageRecipient = createMessageRecipient(message, agentType)
        /* Указываем, что получатель просмотрел сообщение */
        messageRecipient.viewedDate = Date(System.currentTimeMillis())
        messageRecipientService.update(messageRecipient)

        /* Получаем просмотренные сообщения */
        val messageSC = MessageSC()
        messageSC.isViewed = true
        val messages = messageService.get(messageSC)

        /* В списке просмотренных сообщений должно быть сообщение нашего агента - список не пуст */
        assertTrue {
            messages.filter { itMessage ->
                itMessage.recipients.filter {
                    it.isViewed && it.recipient.id == messageRecipient.recipient.id
                }.isNotEmpty()
            }.isNotEmpty()
        }
    }

    /* Тест поиска сообщений по id отправителя */
    @Test
    fun testGetMessagesBySenderId() {
        /* Исходное сообщение */
        val message = messageService.get(id!!)

        /* Получаем сообщения */
        val messageSC = MessageSC()
        messageSC.senderId = message.sender.id
        val messages = messageService.get(messageSC)

        /* В списке просмотренных сообщений должны быть все сообщение нашего агента */
        assertTrue {
            messages.filter { itMessage ->
                itMessage.sender.id!! != message.sender.id!!
            }.isEmpty()
        }
    }

    /* Тест поиска сообщений по типу тела сообщения */
    @Test
    fun testGetMessagesByBodyType() {
        /* Получаем сообщения */
        val messageSC = MessageSC()
        val testTypeCode1 = TypesObjects.testMessageBodyCode1
        messageSC.bodyType = testTypeCode1
        val messages = messageService.get(messageSC)

        /* В списке просмотренных сообщений должны быть все сообщение типа TypesObjects.testMessageBodyCode1 */
        assertTrue {
            messages.filter { itMessage ->
                itMessage.bodyType.code != testTypeCode1
            }.isEmpty()
        }
    }

    /* Тест поиска сообщений по цели сообщения */
    @Test
    fun testGetMessagesByGoalType() {
        /* Получаем сообщения */
        val messageSC = MessageSC()
        val testMessageGoalTypeCode1 = TypesObjects.testMessageGoalCode1
        messageSC.goalType = testMessageGoalTypeCode1
        val messages = messageService.get(messageSC)

        /* В списке просмотренных сообщений должны быть все сообщение типа TypesObjects.testMessageGoalCode1*/
        assertTrue {
            messages.filter { itMessage ->
                itMessage.type.goalType.code != testMessageGoalTypeCode1
            }.isEmpty()
        }
    }

    /* Тест поиска сообщений по типу цели сообщения */
    @Test
    fun testGetMessagesByType() {
        /* Получаем сообщения */
        val messageSC = MessageSC()
        val testMessageGoalTypeCode1 = TypesObjects.testMessageGoalCode1
        messageSC.type = testMessageGoalTypeCode1
        val messages = messageService.get(messageSC)

        /* В списке просмотренных сообщений должны быть все сообщение типа TypesObjects.testMessageGoalCode1 */
        assertTrue {
            messages.filter { itMessage ->
                itMessage.type.code != testMessageGoalTypeCode1
            }.isEmpty()
        }
    }

    /* Тест поиска сообщений по дате создания */
    @Test
    fun testGetMessagesBySinceCreatedDate() {
        /* Получаем сообщения */
        val messageSC = MessageSC()
        /* Берём -5 часов. тк дата в sqlite почему то ошибается на n часов*/
        val time = 1000 * 60 * 60 * 5 // 5 часов
        messageSC.sinceCreatedDate = Date(System.currentTimeMillis() - time)

        /* создание сообщения с позднее даты messageSC.sinceCreatedDate */
        createMessage()
        val messages = messageService.get(messageSC)

        /* В списке просмотренных сообщений должны быть все сообщение с датой больше messageSC.sinceCreatedDate */
        assertTrue {
            messages.filter { itMessage ->
                itMessage.createDate < messageSC.sinceCreatedDate

            }.isEmpty() && !messages.isEmpty()
        }
    }

    /* Тест поиска сообщений по дате просмотра */
    @Test
    fun testGetMessagesBySinceViewedDate() {
        /* Получаем сообщения */
        val messageSC = MessageSC()
        messageSC.sinceViewedDate = createDate

        /* Установка даты просмотра */
        val message = messageService.get(id!!)
        message.recipients.forEach {
            it.viewedDate = createDate
        }
        messageService.update(message)

        /* Получение сообщений*/
        val messages = messageService.get(messageSC)

        /* В списке просмотренных сообщений должны быть сообщения, которые просмотрел пользователь */
        assertTrue {
            !messages.filter { itMessage ->
                !itMessage.recipients.filter {
                    it.isViewed

                }.isEmpty()

            }.isEmpty()
        }
    }

    /* Тест поиска сообщений по получателю */
    @Test
    fun testGetMessagesByRecipientId() {
        /* Сообщение */
        val message = messageService.get(id!!)

        /* Получаем сообщения */
        val messageSC = MessageSC()
        messageSC.recipientAgentId = message.recipients[0].recipient.id
        val messages = messageService.get(messageSC)

        /* В списке просмотренных сообщений должны быть сообщения указанного пользователя */
        assertTrue {
            !messages.filter { itMessage ->
                !itMessage.recipients.filter {
                    it.recipient.id == messageSC.recipientAgentId

                }.isEmpty()

            }.isEmpty()
        }
    }

    /* Тест обновления сообщения в бд */
    @Test
    fun testUpdateMessage() {
        var message = messageService.get(id!!)

        /* новые значения */
        val newSender = createSender()
        val newRecipients = createRecipients(message)
        val newMessageType = messageTypeService.getByCode(TypesObjects.testMessageCode1)
        val newCreateDate = Date(System.currentTimeMillis())
        val newBodyType = messageBodyTypeService.getByCode(TypesObjects.testMessageBodyCode1)
        val newBody = "{ 'a': 1}"

        /* присвоение новых значений */
        message.sender = newSender
        message.recipients = newRecipients
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

    /**
     * MessageRecipient Test
     */

    /* Проверка создания MessageRecipient */
    @Test
    fun testMessageRecipientGetData() {
        val message = messageService.get(id!!)

        message.recipients.forEach {
            assertNotNull(it.id)
            assertNotNull(it.recipient)
            assertNull(it.viewedDate)
        }
    }

    /* Проверка обновления данных MessageRecipient */
    @Test
    fun testMessageRecipientUpdateData() {
        /* Создание объекта */
        val message = messageService.get(id!!)
        val agentType = agentTypeService.getByCode(TypesObjects.testAgentCode1)
        val messageRecipient = createMessageRecipient(message, agentType)

        /* Начальные параметры */
        val oldId = messageRecipient.id!!
        val oldRecipient = messageRecipient.recipient
        val oldViewedDate = messageRecipient.viewedDate

        /* Новые параметры */
        val newRecipient = createAgent(agentType)
        val newViewedDate = Date(System.currentTimeMillis())

        /* Обновление */
        messageRecipient.recipient = newRecipient
        messageRecipient.viewedDate = newViewedDate
        messageRecipientService.update(messageRecipient)

        /* Проверка обновления данных в бд */
        val updateMessageRecipient = messageRecipientService.getById(messageRecipient.id!!)
        assertNotNull(updateMessageRecipient)
        assertEquals(oldId, updateMessageRecipient.id)
        assertNotEquals(oldRecipient.id!!, updateMessageRecipient.recipient.id!!)
        assertEquals(newViewedDate, updateMessageRecipient.viewedDate)
        assertTrue {
            updateMessageRecipient.isViewed
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

    /* Тест использования сообщений для всех агентов*/
    @Test
    fun testUseMessages() {
        val messages = arrayListOf(
                createMessageWithRecipients(),
                createMessageWithRecipients(),
                createMessageWithRecipients()
        )
        messageService.use(messages)

        messages.forEach {
            assertTrue(it.recipients.isNotEmpty())
            messageService.get(it.id!!).recipients.forEach {
                assertTrue(it.isViewed)
            }
        }
    }

    /* Тест использования сообщения для всех его агентов*/
    @Test
    fun testUseMessage() {
        val message = createMessageWithRecipients()
        messageService.use(message)

        val updateMessage = messageService.get(message.id!!)
        assertTrue(updateMessage.recipients.isNotEmpty())
        messageService.get(updateMessage.id!!).recipients.forEach {
            assertTrue(it.isViewed)
        }
    }

    /**
     * Тест использования сообщения конкректного агента
     * Агент - 3 сообщения - используем их все и проверяем, что они заюзаны
     */
    @Test
    fun testUseRecipientMessages() {
        val recipient = createAgent(agentTypeService.getByCode(TypesObjects.testAgentCode1))
        val messages = arrayListOf(
                createMessage(),
                createMessage(),
                createMessage()
        )
        messages.forEach {
            it.recipients = arrayListOf(createMessageRecipient(it, recipient))
            messageService.update(it)
        }

        messageService.use(recipient)

        val sc = MessageSC()
        sc.recipientAgentId = recipient.id!!
        messageService.get(sc).forEach {
            assertTrue(it.recipients.size == 1)
            messageService.get(it.id!!).recipients.forEach {
                assertTrue(it.isViewed)
            }
        }

        sc.isViewed = false
        assertTrue(messageService.get(sc).isEmpty())
    }

    /* Тест использования сообщений для конкректного агента */
    @Test
    fun testUseMessagesByRecipient() {
        val r1_use = createAgent(agentTypeService.getByCode(TypesObjects.testAgentCode1))
        val r2 = createAgent(agentTypeService.getByCode(TypesObjects.testAgentCode1))
        val r3 = createAgent(agentTypeService.getByCode(TypesObjects.testAgentCode1))

        val messages = arrayListOf(createMessage(), createMessage(), createMessage())
        messages.forEach {
            it.recipients = arrayListOf(
                    createMessageRecipient(it, r1_use),
                    createMessageRecipient(it, r2),
                    createMessageRecipient(it, r3)
            )
            messageService.update(it)
        }

        messageService.use(messages, r1_use)

        messages.forEach {
            val message = messageService.get(it.id!!)
            assertTrue(message.recipients.isNotEmpty())
            message.recipients.forEach {
                if (it.recipient.id == r1_use.id) {
                    assertTrue(it.isViewed)
                } else {
                    assertFalse(it.isViewed)
                }
            }
        }
    }

    /* Тест получения непрочитанных сообщений конкректным агентом */
    @Test
    fun testGetNotViewedMessagesWithRecipient() {
        val r1_search_use = createAgent(agentTypeService.getByCode(TypesObjects.testAgentCode1))
        val r2 = createAgent(agentTypeService.getByCode(TypesObjects.testAgentCode1))

        val messages = arrayListOf(createMessage(), createMessage(), createMessage())
        messages.forEach {
            it.recipients = arrayListOf(
                    createMessageRecipient(it, r1_search_use),
                    createMessageRecipient(it, r2)
            )
            messageService.update(it)
        }

        messageService.use(messages, r1_search_use)

        val sc = MessageSC()
        sc.isViewed = false
        sc.recipientAgentId = r1_search_use.id!!
        assertTrue(messageService.get(sc).isEmpty())
    }

    /* Поиск данных без учёта регистра при испоользовании code поля */
    @Test
    fun testGetMessagesWithTypeRegistry() {
        val messages = arrayListOf(createMessage(), createMessage(), createMessage())
        val bodyTypeCode = messages[0].bodyType.code
        val typeCode = messages[0].type.code
        val goalTypeCode = messages[0].type.goalType.code

        var sc = MessageSC()
        arrayListOf(bodyTypeCode.toLowerCase(), bodyTypeCode.toUpperCase()).forEach { code ->
            sc.bodyType = code
            val searchMessages = messageService.get(sc)
            assertTrue {
                searchMessages.isNotEmpty() && searchMessages.all {
                    it.bodyType.code == bodyTypeCode
                }
            }
        }

        sc = MessageSC()
        arrayListOf(goalTypeCode.toLowerCase(), goalTypeCode.toUpperCase()).forEach { code ->
            sc.goalType = code
            val searchMessages = messageService.get(sc)
            assertTrue {
                searchMessages.isNotEmpty() && searchMessages.all {
                    it.type.goalType.code == goalTypeCode
                }
            }
        }

        sc = MessageSC()
        arrayListOf(typeCode.toLowerCase(), typeCode.toUpperCase()).forEach { code ->
            sc.type = code
            val searchMessages = messageService.get(sc)
            assertTrue {
                searchMessages.isNotEmpty() && searchMessages.all {
                    it.type.code == typeCode
                }
            }
        }
    }

    // todo переделать ассерты на этот метод
    private fun assertMessage(expected: Message, actual: Message) {
        /* проверка всех значений создания сообщения */
        assertEquals(expected.id, actual.id!!)
        assertEquals(expected.sender.id!!, actual.sender.id!!)
        assertEquals(expected.recipients.size, actual.recipients.size)
        expected.recipients.forEachIndexed { index, recipient ->
            assertEquals(recipient.recipient.id, actual.recipients[index].recipient.id)
        }
        assertEquals(expected.type.code, actual.type.code)
        assertEquals(expected.type.goalType.code, actual.type.goalType.code)
        assertEquals(expected.bodyType.code, actual.bodyType.code)
        assertEquals(expected.body, actual.body)
        assertNotNull(actual.createDate)
    }

    /* СОЗДАНИЕ ОБЪЕКТОВ */

    /* Создание сообщения */
    private fun createMessage(): Message {
        return createMessage(arrayListOf())
    }

    private fun createMessage(messageRecipients: List<MessageRecipient>): Message {
        val messageType = messageTypeService.getByCode(TypesObjects.testMessageCode1)
        val bodyType = messageBodyTypeService.getByCode(TypesObjects.testMessageBodyCode1)
        sender = createSender()

        val id = messageService.create(Message(
                null,
                sender,
                messageRecipients,
                messageType,
                createDate,
                bodyType,
                body
        ))

        return messageService.get(id)
    }

    private fun createMessageWithRecipients(): Message {
        val message = createMessage()
        message.recipients = createRecipients(message)
        messageService.update(message)

        return messageService.get(message.id!!)
    }

    /* создание отправителя сообщения */
    private fun createSender(): Agent {
        val agentType = agentTypeService.getByCode(TypesObjects.testAgentCode1)
        return createAgent(agentType)
    }

    /* создание получателей сообщения */
    private fun createRecipients(message: Message): List<MessageRecipient> {
        val agentType = agentTypeService.getByCode(TypesObjects.testAgentCode1)
        return arrayListOf(
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

    private fun createMessageRecipient(message: Message, recipient: Agent): MessageRecipient {
        val id = messageRecipientService.create(
                message.id!!,
                MessageRecipient(
                        null,
                        recipient,
                        null
                )
        )
        return messageRecipientService.getById(id)
    }

    private fun createAgent(): Agent {
        val agentType = agentTypeService.getByCode(TypesObjects.testAgentCode1)
        return createAgent(agentType)
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

    private fun createMessageRecipients(number: Int): List<MessageRecipient> {
        val messageRecipients = arrayListOf<MessageRecipient>()
        for (i in 0..number) {
            messageRecipients.add(MessageRecipient(null, createAgent(), null))
        }
        return messageRecipients
    }

    // TODO создание агента без типа агента - любой
}