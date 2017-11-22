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
interface ServerTypeService {

    @GET
    @Path("/getAgentTypes")
    @Description(value = "Получение типов агентов", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun getAgentTypes(): Response

    @GET
    @Path("/getMessageBodyTypes")
    @Description(value = "Получение типов тела сообщения", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun getMessageBodyTypes(): Response

    @GET
    @Path("/getMessageGoalTypes")
    @Description(value = "Получение целей общения", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun getMessageGoalTypes(): Response

    @POST
    @Path("/getMessageTypes")
    @Description(value = "Получение типов сообщений", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun getMessageTypes(@FormParam("goalType") goalType: String?): Response
}