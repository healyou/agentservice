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

    // todo разобраться с tydy

    @POST
    @Path("/registration")
    @Description(value = "Регистрация агента", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun registration(@FormParam("masId") masId: String?,
                     @FormParam("name") name: String?,
                     @FormParam("type") type: String?,
                     @FormParam("password") password: String?): Response

    @POST
    @Path("/login")
    @Description(value = "Авторизация агента", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun login(@FormParam("masId") masId: String?,
              @FormParam("password") password: String?): Response

    @GET
    @Path("/logout")
    @Description(value = "Выход агента", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun logout(): Response
}
