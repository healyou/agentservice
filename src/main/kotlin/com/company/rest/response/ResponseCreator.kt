package com.company.rest.response

import javax.ws.rs.core.NewCookie
import javax.ws.rs.core.Response

/**
 * @author Nikita Gorodilov
 */
object ResponseCreator {

    fun error(status: Int, errorCode: Int, version: String, obj: Any?): Response {
        val response = Response.status(status)
        response.header("version", version)
        response.header("errorcode", errorCode)
        if (obj != null) {
            response.entity(obj)
        } else {
            response.entity("none")
        }
        return response.build()
    }

    fun error(status: Int, errorCode: Int, version: String): Response {
        val response = Response.status(status)
        response.header("version", version)
        response.header("errorcode", errorCode)
        response.entity("none")
        return response.build()
    }

    fun success(version: String, obj: Any?): Response {
        val response = Response.ok()
        response.header("version", version)
        if (obj != null) {
            response.entity(obj)
        } else {
            response.entity("none")
        }
        return response.build()
    }

    fun success(version: String): Response {
        val response = Response.ok()
        response.header("version", version)
        return response.build()
    }

    fun success(version: String, obj: Any?, cookie: NewCookie): Response {
        val response = Response.ok()
        response.cookie(cookie)
        response.header("version", version)
        if (obj != null) {
            response.entity(obj)
        } else {
            response.entity("none")
        }
        return response.build()
    }
}
