package com.company.rest

import com.company.rest.response.ResponseCreator
import javax.ws.rs.core.Response

/**
 * @author Nikita Gorodilov
 */
class LoginServiceImpl: BaseServer(), LoginService {

    // todo - структура базы данных сервиса

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