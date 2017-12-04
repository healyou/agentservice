package com.company.rest.response

import com.company.rest.BaseServer
import org.apache.cxf.interceptor.security.AccessDeniedException
import org.apache.cxf.message.Message
import org.apache.cxf.phase.AbstractPhaseInterceptor
import org.apache.cxf.phase.Phase
import org.apache.cxf.transport.http.AbstractHTTPDestination
import javax.servlet.http.HttpServletRequest

/**
 * @author Nikita Gorodilov
 */
class AuthorizingInterceptor : AbstractPhaseInterceptor<Message>(Phase.PRE_INVOKE) {

    override fun handleMessage(message: Message) {
        val urlAsked = message["path_to_match_slash"] as String
        val isNeedAuth = !isLoginOrRegistrationUrl(urlAsked) && !isTypeUrl(urlAsked)

        if (isNeedAuth) {
            val request = message[AbstractHTTPDestination.HTTP_REQUEST] as HttpServletRequest
            val session = request.session
            session.getAttribute(BaseServer.MAS_ID) ?: throw AccessDeniedException("Unauthorized")
        }
    }

    private fun isLoginOrRegistrationUrl(urlAsked: String): Boolean {
        return "/login".equals(urlAsked, ignoreCase = true) ||
                "/registration".equals(urlAsked, ignoreCase = true)
    }

    private fun isTypeUrl(urlAsked: String): Boolean {
        return "/getAgentTypes".equals(urlAsked, ignoreCase = true) ||
                "/getMessageBodyTypes".equals(urlAsked, ignoreCase = true) ||
                "/getMessageGoalTypes".equals(urlAsked, ignoreCase = true) ||
                "/getMessageTypes".equals(urlAsked, ignoreCase = true)
    }
}