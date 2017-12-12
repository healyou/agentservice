package com.company.rest

import com.company.rest.utils.Documentation
import org.apache.cxf.jaxrs.model.wadl.Description
import org.apache.cxf.jaxrs.model.wadl.Descriptions
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
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Получение типов агентов",
                    target = DocTarget.METHOD),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsOptional,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.getTypesResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.agentTypesReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun getAgentTypes(): Response

    @GET
    @Path("/getMessageBodyTypes")
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Получение типов тела сообщения",
                    target = DocTarget.METHOD),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsOptional,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.getTypesResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.messageBodyTypesReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun getMessageBodyTypes(): Response

    @GET
    @Path("/getMessageGoalTypes")
    @Description(value = "Получение целей общения", target = DocTarget.METHOD)
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Получение целей общения",
                    target = DocTarget.METHOD),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsOptional,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.getTypesResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.messageGoalTypesReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun getMessageGoalTypes(): Response

    @POST
    @Path("/getMessageTypes")
    @Description(value = "Получение типов сообщений по цели общения", target = DocTarget.METHOD)
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Получение типов сообщений по цели общения",
                    target = DocTarget.METHOD),
            Description(title = "Описание запроса",
                    value = Documentation.allParamsIsOptional,
                    target = DocTarget.REQUEST),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsOptional,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.getPostMessageTypesReturnValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.messageTypesReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun getMessageTypes(@FormParam("goalType")
                        @Description(title = "Описание параметра",
                                value = Documentation.goalTypeParamValue,
                                target = DocTarget.PARAM) goalType: String?): Response

    @GET
    @Path("/getMessageTypes")
    @Description(value = "Получение типов сообщений", target = DocTarget.METHOD)
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Получение типов сообщений",
                    target = DocTarget.METHOD),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsOptional,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.getTypesResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.messageTypesReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun getMessageTypes(): Response
}