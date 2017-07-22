package com.company.rest

import org.apache.cxf.jaxrs.model.wadl.Description
import org.apache.cxf.jaxrs.model.wadl.DocTarget
import javax.jws.WebService
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * @author Nikita Gorodilov
 */
@WebService
@Produces(MediaType.APPLICATION_JSON)
interface LoginService {

    @POST
    @Path("/login")
    @Description(value = "Авторизация пользователя", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun login(@FormParam("login") login: String, @FormParam("password") password: String): Response

    @GET
    @Path("/logout")
    @Description(value = "Выход пользователя", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun logout(): Response
}
