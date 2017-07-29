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
        val isNeedAuth = !"/login".equals(urlAsked, ignoreCase = true) &&
                !"/registration".equals(urlAsked, ignoreCase = true)

        if (isNeedAuth) {
            val request = message[AbstractHTTPDestination.HTTP_REQUEST] as HttpServletRequest
            val session = request.session
            session.getAttribute(BaseServer.MAS_ID) ?: throw AccessDeniedException("Unauthorized")
        }
    }
}