package com.company.rest

import com.company.db.base.Codable
import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentService
import com.company.db.core.agent.AgentType
import com.company.db.core.agent.AgentTypeService
import com.company.rest.exceptions.AgentError
import com.company.rest.exceptions.Error
import com.company.rest.response.ResponseCreator
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import javax.ws.rs.core.Response

/**
 * Вход агента в сервис
 *
 * @author Nikita Gorodilov
 */
class LoginServiceImpl: BaseServer(), LoginService {

    // todo как тестить работу сервиса?

    @Autowired
    lateinit var agentService: AgentService

    @Autowired
    lateinit var agentTypeService: AgentTypeService

    /* тестовый пароль для авторизации агентов */
    private val TEST_PASSWORD = "psw"

    /**
     * Регистрация агента в сервисе
     *
     * @param masId уникальный идентификатор агента
     * @param name имя агента
     * @param type тип агента
     * @param password пароль - чтобы не накидали лишней инфы
     */
    @Throws(Exception::class)
    override fun registration(masId: String?, name: String?, type: String?, password: String?): Response {
        /* Проверка параметров*/
        if (Utils.isNull(masId, name, type, password) || password != TEST_PASSWORD) {
            return error("Неверное значение параметров")
        }

        /* Регистрация агента */
        try {
            val agentTypeCode = Codable.find(AgentType.Code::class.java, type!!)
            val id = agentService.create(Agent(
                    null,
                    masId!!,
                    name!!,
                    agentTypeService.get(agentTypeCode),
                    Date(System.currentTimeMillis()),
                    false
            ))

            return ResponseCreator.success(headerVersion, agentService.get(id))
        } catch (e: Exception) {
            return error("Ошибка регистрации агента")
        }
    }

    /**
     * Авторизация агента в сервисе
     *
     * @param masId уникальный идентификатор агента
     * @param password пароль
     */
    @Throws(Exception::class)
    override fun login(masId: String?, password: String?): Response {
        /* Проверка параметров*/
        if (Utils.isNull(masId, password)) {
            return error("Параметр имеет значение null")
        }

        /* Авторизация агента */
        if (password!! == TEST_PASSWORD) {
            try {
                val agent = agentService.getByMasId(masId!!)

                /* закрываем сессию, если была */
                var session = httpSession
                session.invalidate()
                /* создаём новую сессию */
                session = httpSession
                session.setAttribute(MAS_ID, masId)

                return ResponseCreator.success(headerVersion, agent)
            } catch (e: Exception) {
                /* Ошибка будет, если не сможем найти агента по masId */
                return error("Ошибка авторизации")
            }
        } else {
            return error("Ошибка авторизации")
        }
    }

    /**
     * Выход агента с сервиса
     */
    @Throws(Exception::class)
    override fun logout(): Response {
        httpSession.invalidate()

        val test = arrayListOf<Long>(1, 2, 3 , 4, 5)

        return ResponseCreator.success(headerVersion, test)
    }

    private fun error(message: String): Response {
        return ResponseCreator.error(
                Error.SERVER_ERROR.code,
                Error.SERVER_ERROR.code,
                headerVersion,
                AgentError(message)
        )
    }
}