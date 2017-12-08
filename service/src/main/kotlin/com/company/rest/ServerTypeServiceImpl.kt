package com.company.rest

import com.company.db.base.Codable
import com.company.db.core.agent.AgentTypeService
import com.company.db.core.message.*
import com.company.rest.response.ResponseCreator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.core.Response

/**
 * Получение всех словарей(типов)
 *
 * @author Nikita Gorodilov
 */
class ServerTypeServiceImpl : BaseServer(), ServerTypeService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var agentTypeService: AgentTypeService
    @Autowired
    private lateinit var messageGoalTypeService: MessageGoalTypeService
    @Autowired
    private lateinit var messageTypeService: MessageTypeService
    @Autowired
    private lateinit var messageBodyTypeService: MessageBodyTypeService

    override fun getAgentTypes(): Response {
        log("Получение типов агентов")

        return ResponseCreator.success(headerVersion, agentTypeService.get())
    }

    override fun getMessageBodyTypes(): Response {
        log("Получение типов тела сообщений")

        return ResponseCreator.success(headerVersion, messageBodyTypeService.get())
    }

    override fun getMessageGoalTypes(): Response {
        log("Получение целей общения")

        return ResponseCreator.success(headerVersion, messageGoalTypeService.get())
    }

    /**
     * Список типов сообщений для конкретной цели общения
     *
     * @param goalType цель общения
     */
    override fun getMessageTypes(goalType: String?): Response {
        log("Получение типов сообщений по цели общения")

        if (Utils.isNull(goalType)) {
            return errorMessageResponse("Параметр имеет значение null")
        }

        val messageGoalTypeCode = Codable.find(MessageGoalType.Code::class.java, goalType!!)
        val messageGoalType = messageGoalTypeService.get(messageGoalTypeCode)

        return ResponseCreator.success(headerVersion, messageTypeService.get(messageGoalType))
    }

    override fun getMessageTypes(): Response {
        log("Получение типов сообщений")

        return ResponseCreator.success(headerVersion, messageTypeService.get())
    }

    override fun getLogger(): Logger = logger
}