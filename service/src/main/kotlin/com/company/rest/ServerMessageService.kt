package com.company.rest

import org.apache.cxf.jaxrs.model.wadl.Description
import org.apache.cxf.jaxrs.model.wadl.DocTarget
import java.util.*
import javax.jws.WebService
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * @author Nikita Gorodilov
 */
@WebService
@Produces(MediaType.APPLICATION_JSON)
interface ServerMessageService {

    @POST
    @Path("/sendMessage")
    @Description(value = "Отправка сообщения", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun sendMessage(@FormParam("goalType") goalType: String?,
                    @FormParam("type") type: String?,
                    @FormParam("recipientsIds") recipientsIds: List<Long>?,
                    @FormParam("bodyType") bodyType: String?,
                    @FormParam("body") body: String?): Response

    @POST
    @Path("/getMessages")
    @Description(value = "Получение сообщений", target = DocTarget.METHOD)
    @Throws(Exception::class)
    fun getMessages(@FormParam("goalType") goalType: String?,
                    @FormParam("type") type: String?,
                    @FormParam("bodyType") bodyType: String?,
                    @FormParam("senderId") senderId: Long?,
                    @FormParam("isViewed") isViewed: Boolean?,
                    @FormParam("sinceCreatedDate") sinceCreatedDate: Date?,
                    @FormParam("sinceViewedDate") sinceViewedDate: Date?): Response
}