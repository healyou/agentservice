package com.company.rest

import com.company.db.base.Codable
import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentService
import com.company.db.core.agent.AgentType
import com.company.db.core.agent.AgentTypeService
import com.company.rest.response.ResponseCreator
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import javax.ws.rs.core.Response
import org.slf4j.LoggerFactory





/**
 * Вход агента в сервис
 *
 * @author Nikita Gorodilov
 */
class LoginServiceImpl: BaseServer(), LoginService {

    private val logger = LoggerFactory.getLogger(javaClass)

    // todo как тестить работу сервиса?

    @Autowired
    private lateinit var agentService: AgentService

    @Autowired
    private lateinit var agentTypeService: AgentTypeService

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
        log("Регистрация агента", masId)

        /* Проверка параметров*/
        if (Utils.isNull(masId, name, type, password) || password != TEST_PASSWORD) {
            return errorMessageResponse("Неверное значение параметров")
        }

        /* Регистрация агента */
        return try {
            val agentTypeCode = Codable.find(AgentType.Code::class.java, type!!)
            val id = agentService.create(Agent(
                    null,
                    masId!!,
                    name!!,
                    agentTypeService.get(agentTypeCode),
                    Date(System.currentTimeMillis()),
                    false
            ))

            ResponseCreator.success(headerVersion, agentService.get(id))
        } catch (e: Exception) {
            errorMessageResponse("Ошибка регистрации агента")
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
        log("Вход агента", masId)

        /* Проверка параметров*/
        if (Utils.isNull(masId, password)) {
            return errorMessageResponse("Параметр имеет значение null")
        }

        /* Авторизация агента */
        if (password!! == TEST_PASSWORD) {
            return try {
                val agent = agentService.getByMasId(masId!!)

                /* закрываем сессию, если была */
                var session = httpSession
                session.invalidate()
                /* создаём новую сессию */
                session = httpSession
                session.setAttribute(MAS_ID, masId)

                ResponseCreator.success(headerVersion, agent)
            } catch (e: Exception) {
                /* Ошибка будет, если не сможем найти агента по masId */
                errorMessageResponse("Ошибка авторизации")
            }
        } else {
            return errorMessageResponse("Ошибка авторизации")
        }
    }

    /**
     * Выход агента с сервиса
     */
    @Throws(Exception::class)
    override fun logout(): Response {
        log("Выход агента")
        httpSession.invalidate()

        return ResponseCreator.success(headerVersion)
    }

    override fun getLogger(): Logger {
        return logger
    }
}