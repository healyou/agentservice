package com.company.rest

import com.company.rest.utils.Documentation
import org.apache.cxf.jaxrs.model.wadl.Description
import org.apache.cxf.jaxrs.model.wadl.Descriptions
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
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Отправка сообщения",
                    target = DocTarget.METHOD),
            Description(title = "Описание запроса",
                    value = Documentation.allParamsIsRequired,
                    target = DocTarget.REQUEST),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsRequired,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.sendMessageResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.messageJsonReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun sendMessage(@FormParam("type")
                    @Description(title = "Описание параметра",
                            value = Documentation.messageTypeParamValue,
                            target = DocTarget.PARAM) type: String?,
                    @FormParam("recipientsIds")
                    @Description(title = "Описание параметра",
                            value = "Идентификаторы получателей сообщения(агентов)",
                            target = DocTarget.PARAM) recipientsIds: List<Long>?,
                    @FormParam("bodyType")
                    @Description(title = "Описание параметра",
                            value = Documentation.messageBodyTypeParamValue,
                            target = DocTarget.PARAM) bodyType: String?,
                    @FormParam("body")
                    @Description(title = "Описание параметра",
                            value = "Тело сообщения",
                            target = DocTarget.PARAM) body: String?): Response

    @POST
    @Path("/getMessages")
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Получение сообщений текущего агента",
                    target = DocTarget.METHOD),
            Description(title = "Описание запроса",
                    value = Documentation.allParamsIsOptional,
                    target = DocTarget.REQUEST),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsRequired,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.getMessagesResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.messagesJsonReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun getMessages(@FormParam("goalType")
                    @Description(title = "Описание параметра",
                            value = Documentation.goalTypeParamValue,
                            target = DocTarget.PARAM) goalType: String?,
                    @FormParam("type")
                    @Description(title = "Описание параметра",
                            value = Documentation.messageTypeParamValue,
                            target = DocTarget.PARAM) type: String?,
                    @FormParam("bodyType")
                    @Description(title = "Описание параметра",
                            value = Documentation.messageBodyTypeParamValue,
                            target = DocTarget.PARAM) bodyType: String?,
                    @FormParam("senderId")
                    @Description(title = "Описание параметра",
                            value = "Идентификатор отправителя сообщения(агента)",
                            target = DocTarget.PARAM) senderId: Long?,
                    @FormParam("isViewed")
                    @Description(title = "Описание параметра",
                            value = Documentation.isViewedParamValue,
                            target = DocTarget.PARAM) isViewed: Boolean?,
                    @FormParam("sinceCreatedDate")
                    @Description(title = "Описание параметра",
                            value = "С даты создания сообщения",
                            target = DocTarget.PARAM) sinceCreatedDate: Date?,
                    @FormParam("sinceViewedDate")
                    @Description(title = "Описание параметра",
                            value = "С даты просмотра сообщения",
                            target = DocTarget.PARAM) sinceViewedDate: Date?): Response
}