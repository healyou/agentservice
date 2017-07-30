package com.company.rest

import com.company.db.base.Codable
import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentService
import com.company.db.core.message.*
import com.company.rest.exceptions.AgentError
import com.company.rest.exceptions.Error
import com.company.rest.response.ResponseCreator
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

    @Autowired
    lateinit var agentService: AgentService
    @Autowired
    lateinit var messageService: MessageService
    @Autowired
    lateinit var messageGoalTypeService: MessageGoalTypeService
    @Autowired
    lateinit var messageTypeService: MessageTypeService
    @Autowired
    lateinit var messageBodyTypeService: MessageBodyTypeService

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
    override fun sendMessage(goalType:String?,
                             type: String?,
                             recipientsIds: List<Long>?,
                             bodyType: String?,
                             body: String?): Response {
        /* Проверка параметров*/
        if (Utils.isNull(goalType, type, bodyType, recipientsIds, body)) {
            return error("Параметр имеет значение null")
        }

        try {
            /* Цель сообщения */
            val goalTypeCode = Codable.find(MessageGoalType.Code::class.java, goalType!!)
            val messageGoalType = messageGoalTypeService.get(goalTypeCode)

            /* Тип цели сообщения */
            val typeCode = Codable.find(MessageType.Code::class.java, type!!)
            val messageType = messageTypeService.get(typeCode)

            /* Тип тела сообщения */
            val bodyTypeCode = Codable.find(MessageBodyType.Code::class.java, bodyType!!)
            val messageBodyType = messageBodyTypeService.get(bodyTypeCode)

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
                    messageGoalType,
                    messageType,
                    Date(System.currentTimeMillis()),
                    messageBodyType,
                    body!!
            ))

            return ResponseCreator.success(headerVersion, messageService.get(messageId))
        } catch (e: Exception) {
            /* Ошибка возникнет, если не будут найдены в бд необходимые параметры типов данных */
            return error("Ошибка отправки сообщения")
        }
    }

    fun error(message: String): Response {
        return ResponseCreator.error(
                Error.SERVER_ERROR.code,
                Error.SERVER_ERROR.code,
                headerVersion,
                AgentError(message)
        )
    }
}