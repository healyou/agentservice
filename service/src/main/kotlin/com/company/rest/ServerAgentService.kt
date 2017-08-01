package com.company.rest

import org.apache.cxf.jaxrs.model.wadl.Description
import org.apache.cxf.jaxrs.model.wadl.DocTarget
import javax.jws.WebService
import javax.ws.rs.FormParam
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * @author Nikita Gorodilov
 */
@WebService
@Produces(MediaType.APPLICATION_JSON)
interface ServerAgentService {

    @POST
    @Path("/getCurrentAgent")
    @Description(value = "Получение текущего агента", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun getCurrentAgent(): Response

    @POST
    @Path("/getAgents")
    @Description(value = "Получение списка агентов с параметрами", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun getAgents(@FormParam("type") type: String?,
                  @FormParam("isDeleted") isDeleted: Boolean?): Response
}