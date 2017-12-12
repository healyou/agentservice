package com.company.rest

import com.company.rest.utils.Documentation.agentJsonReturnValue
import com.company.rest.utils.Documentation.allParamsRequired
import com.company.rest.utils.Documentation.loginIsOptional
import com.company.rest.utils.Documentation.loginRequired
import com.company.rest.utils.Documentation.loginResponseValue
import com.company.rest.utils.Documentation.logoutResponseValue
import com.company.rest.utils.Documentation.masIdParamValue
import com.company.rest.utils.Documentation.passwordParamValue
import com.company.rest.utils.Documentation.registrationResponseValue
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
interface LoginService {

    @POST
    @Path("/registration")
    @Throws(Exception::class)
    @Descriptions(
            Description(title = "Описание метода", value = "Регистрация агента", target = DocTarget.METHOD),
            Description(title = "Описание запроса", value = allParamsRequired, target = DocTarget.REQUEST),
            Description(title = "Необходимость входа в систему для выполнения метода", value = loginIsOptional, target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов", value = registrationResponseValue, target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных", value = agentJsonReturnValue, target = DocTarget.RETURN)
    )
    fun registration(@FormParam("masId")
                     @Description(title = "Описание параметра",
                             value = masIdParamValue,
                             target = DocTarget.PARAM) masId: String?,
                     @FormParam("name")
                     @Description(title = "Описание параметра",
                             value = "Имя агента",
                             target = DocTarget.PARAM) name: String?,
                     @FormParam("type")
                     @Description(title = "Описание параметра",
                             value = "Тип агента(поле code таблицы agent_type)",
                             target = DocTarget.PARAM) type: String?,
                     @FormParam("password")
                     @Description(title = "Описание параметра",
                             value = passwordParamValue,
                             target = DocTarget.PARAM) password: String?): Response

    @POST
    @Path("/login")
    @Descriptions(
            Description(title = "Описание метода", value = "Авторизация агента в сервисе", target = DocTarget.METHOD),
            Description(title = "Описание запроса", value = allParamsRequired, target = DocTarget.REQUEST),
            Description(title = "Необходимость входа в систему для выполнения метода", value = loginIsOptional, target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов", value = loginResponseValue, target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных", value = agentJsonReturnValue, target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun login(@FormParam("masId")
              @Description(title = "Описание параметра",
                      value = masIdParamValue,
                      target = DocTarget.PARAM) masId: String?,
              @FormParam("password")
              @Description(title = "Описание параметра",
                      value = passwordParamValue,
                      target = DocTarget.PARAM) password: String?): Response

    @GET
    @Path("/logout")
    @Descriptions(
            Description(title = "Описание метода", value = "Выход агента", target = DocTarget.METHOD),
            Description(title = "Необходимость входа в систему для выполнения метода", value = loginRequired, target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов", value = logoutResponseValue, target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных", value = agentJsonReturnValue, target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun logout(): Response
}
