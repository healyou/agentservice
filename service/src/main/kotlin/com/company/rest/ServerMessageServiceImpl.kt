package com.company.rest

import com.company.db.core.agent.AgentService
import com.company.db.core.message.*
import com.company.db.core.sc.MessageSC
import com.company.rest.response.ResponseCreator
import com.company.rest.utils.Utils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import javax.ws.rs.core.Response

/**
 * Отправка и получения сообщений агентов
 *      Выполнять могут только авторизованные агенты
 *
 * @author Nikita Gorodilov
 */
class ServerMessageServiceImpl : BaseServer(), ServerMessageService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var agentService: AgentService
    @Autowired
    private lateinit var messageService: MessageService
    @Autowired
    private lateinit var messageGoalTypeService: MessageGoalTypeService
    @Autowired
    private lateinit var messageTypeService: MessageTypeService
    @Autowired
    private lateinit var messageBodyTypeService: MessageBodyTypeService

    /**
     * Отправка сообщения другим агентам
     *
     * @param goalType цель сообщения
     * @param type тип цели сообщения
     * @param recipientsIds получатели сообщения(id агентов в бд)
     * @param bodyType тип тела сообщения
     * @param body тело сообщения
     */
    @Throws(Exception::class)
    override fun sendMessage(type: String?,
                             recipientsIds: List<Long>?,
                             bodyType: String?,
                             body: String?): Response {
        log("Отправка сообщения")

        /* Проверка параметров*/
        if (Utils.isNull(type, bodyType, recipientsIds, body)) {
            return errorMessageResponse("Параметр имеет значение null")
        }

        return try {
            /* Тип цели сообщения */
            val messageType = messageTypeService.getByCode(type!!)
            /* Тип тела сообщения */
            val messageBodyType = messageBodyTypeService.getByCode(bodyType!!)
            /* Получатели сообщения */
            val recipients = arrayListOf<MessageRecipient>()
            recipientsIds!!.forEach {
                recipients.add(MessageRecipient(
                        null,
                        agentService.get(it),
                        null
                ))
            }

            /* Отправитель сообщения */
            val sender = agentService.getByMasId(currentAgentMasId!!)

            /* Создание сообщения */
            val messageId = messageService.create(Message(
                    null,
                    sender,
                    recipients,
                    messageType,
                    Date(System.currentTimeMillis()),
                    messageBodyType,
                    body!!
            ))

            ResponseCreator.success(headerVersion, messageService.get(messageId))
        } catch (e: Exception) {
            /* Ошибка возникнет, если не будут найдены в бд необходимые параметры типов данных */
            errorMessageResponse("Ошибка отправки сообщения")
        }
    }

    /**
     * Получения сообщений авторизованного агента
     *      На входе параметры для поиска по бж
     *      Все параметры не обязательные
     *
     * @param goalType цель сообщения
     * @param type тип цель сообщения
     * @param bodyType тип тела сообщения
     * @param senderId отправитель сообщения
     * @param isViewed просмотрено ли
     * @param sinceCreatedDate с даты создания сообщения
     * @param sinceViewedDate с даты просмотра сообщения
     */
    override fun getMessages(goalType: String?,
                             type: String?,
                             bodyType: String?,
                             senderId: Long?,
                             isViewed: Boolean?,
                             sinceCreatedDate: Date?,
                             sinceViewedDate: Date?): Response {
        log("Получение списка сообщений")

        return try {
            val currentAgent = agentService.getByMasId(currentAgentMasId!!)

            /* Поисковые данные */
            val messageSC = MessageSC()
            messageSC.isViewed = isViewed
            messageSC.senderId = senderId
            messageSC.goalType = goalType
            messageSC.bodyType = bodyType
            messageSC.type = type
            messageSC.sinceCreatedDate = sinceCreatedDate
            messageSC.sinceViewedDate = sinceViewedDate
            messageSC.recipientAgentId = currentAgent.id

            val messages = messageService.get(messageSC)
            messageService.use(messages, currentAgent)

            ResponseCreator.success(headerVersion, messages)
        } catch (e: Exception) {
            errorMessageResponse("Ошибка поиска сообщений")
        }
    }

    override fun getLogger(): Logger = logger
}