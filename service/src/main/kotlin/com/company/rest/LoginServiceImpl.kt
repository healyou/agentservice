package com.company.rest

import com.company.db.core.agent.AgentService
import com.company.db.core.agent.AgentType
import com.company.db.core.agent.AgentTypeService
import com.company.rest.response.ResponseCreator
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.core.Response

/**
 * @author Nikita Gorodilov
 */
class LoginServiceImpl: BaseServer(), LoginService {

    @Autowired
    lateinit var service: AgentService

    private inner class Test {
        var gg1 = "asd"
        var test: Test? = null
    }

    @Throws(Exception::class)
    override fun login(login: String, password: String): Response {
        var session = httpSession
        session.invalidate()
        session = httpSession
        session.setAttribute(USER_LOGIN, login)

        val agents = service.get()
        val agent = service.get(1)

        val test = Test()
        test.test = Test()
        return ResponseCreator.success(headerVersion, test)
    }

    @Throws(Exception::class)
    override fun logout(): Response {
        httpSession.invalidate()
        return ResponseCreator.success(headerVersion, null)
    }
}