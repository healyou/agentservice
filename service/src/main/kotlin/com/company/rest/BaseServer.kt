package com.company.rest

import com.company.rest.exceptions.AgentError
import com.company.rest.exceptions.Error
import com.company.rest.response.ResponseCreator
import org.apache.cxf.phase.PhaseInterceptorChain
import org.apache.cxf.transport.http.AbstractHTTPDestination
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import javax.ws.rs.core.Response

/**
 * @author Nikita Gorodilov
 */
abstract class BaseServer {

    companion object {
        val MAS_ID = "user_login"
    }

    protected val headerVersion: String
        get() = "1.0"

    protected val httpSession: HttpSession
        get() {
            val message = PhaseInterceptorChain.getCurrentMessage()
            val request = message[AbstractHTTPDestination.HTTP_REQUEST] as HttpServletRequest
            return request.getSession(true)
        }

    /**
     * Уникальный идентификатор агента в сесии
     */
    protected val currentAgentMasId: String?
        get() {
            val session = httpSession
            return session.getAttribute(MAS_ID) as String?
        }

    @Throws(NoSuchAlgorithmException::class)
    protected fun getMD5(plaintext: String): String {
        val m = MessageDigest.getInstance("MD5")
        m.reset()
        m.update(plaintext.toByteArray())
        val digest = m.digest()
        val bigInt = BigInteger(1, digest)
        var hashtext = bigInt.toString(16)
        while (hashtext.length < 32) {
            hashtext = "0" + hashtext
        }
        return hashtext
    }

    /* Вывод сообщения с ошибкой */
    protected fun errorMessageResponse(message: String): Response {
        return ResponseCreator.error(
                Error.FORBIDDEN.code,
                Error.FORBIDDEN.code,
                headerVersion,
                AgentError(message)
        )
    }
}
