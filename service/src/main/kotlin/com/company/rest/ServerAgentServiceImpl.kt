package com.company.rest

import com.company.db.core.agent.AgentService
import com.company.db.core.sc.AgentSC
import com.company.rest.response.ResponseCreator
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.core.Response


/**
 * Работа с агентами в сервисе
 *
 * @author Nikita Gorodilov
 */
class ServerAgentServiceImpl: BaseServer(), ServerAgentService {

    @Autowired
    private lateinit var agentService: AgentService

    /**
     * Получение списка агентов с параметрами, кроме текущего агента
     *
     * @param type тип агента
     * @param isDeleted удалён ли агента
     */
    override fun getAgents(type: String?, isDeleted: Boolean?): Response {
        return try {
            /* Параметры запроса */
            val sc = AgentSC()
            sc.type = type
            sc.isDeleted = isDeleted

            /* ищем всех агентов, кроме текущего */
            val agents = agentService.get(sc).filter {
                it.masId != currentAgentMasId
            }

            ResponseCreator.success(headerVersion, agents)
        } catch (e: Exception) {
            errorMessageResponse("Ошибка поиска агентов")
        }
    }

    /**
     * Получить текущего агента
     */
    override fun getCurrentAgent(): Response {
        return try {
            val agent = agentService.getByMasId(currentAgentMasId!!)

            ResponseCreator.success(headerVersion, agent)
        } catch (e: Exception) {
            errorMessageResponse("Не найден текущий агент")
        }
    }
}