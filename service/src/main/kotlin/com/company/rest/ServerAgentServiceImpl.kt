package com.company.rest

import com.company.db.core.agent.AgentService
import com.company.db.core.sc.AgentSC
import com.company.rest.response.ResponseCreator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.core.Response


/**
 * Работа с агентами в сервисе
 *
 * @author Nikita Gorodilov
 */
class ServerAgentServiceImpl: BaseServer(), ServerAgentService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var agentService: AgentService

    /**
     * Получение списка агентов с параметрами, кроме текущего агента
     *
     * @param type тип агента
     * @param isDeleted удалён ли агента
     */
    override fun getAgents(type: String?, isDeleted: Boolean?): Response {
        log("Получение списка агентов")

        try {
            /* Параметры запроса */
            val sc = AgentSC()
            sc.type = type
            sc.isDeleted = isDeleted

            /* ищем всех агентов, кроме текущего */
            val agents = agentService.get(sc).filter {
                it.masId != currentAgentMasId
            }

            return ResponseCreator.success(headerVersion, agents)
        } catch (e: Exception) {
            return errorMessageResponse("Ошибка поиска агентов")
        }
    }

    /**
     * Получить текущего агента
     */
    override fun getCurrentAgent(): Response {
        log("Получение текущего агента")

        try {
            val agent = agentService.getByMasId(currentAgentMasId!!)

            return ResponseCreator.success(headerVersion, agent)
        } catch (e: Exception) {
            return errorMessageResponse("Не найден текущий агент")
        }
    }

    override fun getLogger(): Logger {
        return logger
    }
}