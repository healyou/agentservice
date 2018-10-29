package com.company.rest.exceptions

import com.company.rest.response.ResponseCreator
import org.apache.cxf.interceptor.security.AccessDeniedException
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

import com.company.rest.exceptions.Error.SERVER_ERROR

/**
 * @author Nikita Gorodilov
 */
class CustomExceptionMapper : ExceptionMapper<Exception> {

    private val headerVersion: String
        get() = "0.1"

    override fun toResponse(ex: Exception): Response {
        println(ex.message + ex.cause)
        if (ex is AccessDeniedException) {
            return ResponseCreator.error(
                    Error.NOT_AUTHORIZED.code,
                    Error.NOT_AUTHORIZED.code,
                    headerVersion
            )
        }
        if (ex is ValidException) {
            return ResponseCreator.error(
                    Error.NOT_VALID.code,
                    Error.NOT_VALID.code,
                    headerVersion,
                    ex.errorStr
            )
        }
        return ResponseCreator.error(500, SERVER_ERROR.code, headerVersion)
    }
}
