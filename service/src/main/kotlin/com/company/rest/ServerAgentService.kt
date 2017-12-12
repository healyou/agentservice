package com.company.rest

import com.company.rest.utils.Documentation
import org.apache.cxf.jaxrs.model.wadl.Description
import org.apache.cxf.jaxrs.model.wadl.Descriptions
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
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Получение экземпляра текущего агента",
                    target = DocTarget.METHOD),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsRequired,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.getCurrentAgentResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.agentJsonReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun getCurrentAgent(): Response

    @POST
    @Path("/getAgents")
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Получение списка агентов системы(кроме текущего)",
                    target = DocTarget.METHOD),
            Description(title = "Описание запроса",
                    value = Documentation.allParamsIsOptional,
                    target = DocTarget.REQUEST),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsRequired,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.getAgentsResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.agentsJsonReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun getAgents(@FormParam("type")
                  @Description(title = "Описание параметра",
                          value = Documentation.agentTypeParamValue,
                          target = DocTarget.PARAM) type: String?,
                  @FormParam("isDeleted")
                  @Description(title = "Описание параметра",
                          value = Documentation.isDeletedParamValue,
                          target = DocTarget.PARAM) isDeleted: Boolean?): Response
}