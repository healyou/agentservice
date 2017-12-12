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
interface LoginService {

    @POST
    @Path("/registration")
    @Throws(Exception::class)
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Регистрация агента",
                    target = DocTarget.METHOD),
            Description(title = "Описание запроса",
                    value = Documentation.allParamsIsRequired,
                    target = DocTarget.REQUEST),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsOptional,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.registrationResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.agentJsonReturnValue,
                    target = DocTarget.RETURN)
    )
    fun registration(@FormParam("masId")
                     @Description(title = "Описание параметра",
                             value = Documentation.masIdParamValue,
                             target = DocTarget.PARAM) masId: String?,
                     @FormParam("name")
                     @Description(title = "Описание параметра",
                             value = "Имя агента",
                             target = DocTarget.PARAM) name: String?,
                     @FormParam("type")
                     @Description(title = "Описание параметра",
                             value = Documentation.agentTypeParamValue,
                             target = DocTarget.PARAM) type: String?,
                     @FormParam("password")
                     @Description(title = "Описание параметра",
                             value = Documentation.passwordParamValue,
                             target = DocTarget.PARAM) password: String?): Response

    @POST
    @Path("/login")
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Авторизация агента в сервисе",
                    target = DocTarget.METHOD),
            Description(title = "Описание запроса",
                    value = Documentation.allParamsIsRequired,
                    target = DocTarget.REQUEST),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsOptional,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.loginResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.agentJsonReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun login(@FormParam("masId")
              @Description(title = "Описание параметра",
                      value = Documentation.masIdParamValue,
                      target = DocTarget.PARAM) masId: String?,
              @FormParam("password")
              @Description(title = "Описание параметра",
                      value = Documentation.passwordParamValue,
                      target = DocTarget.PARAM) password: String?): Response

    @GET
    @Path("/logout")
    @Descriptions(
            Description(title = "Описание метода",
                    value = "Выход агента",
                    target = DocTarget.METHOD),
            Description(title = "Необходимость входа в систему для выполнения метода",
                    value = Documentation.loginIsRequired,
                    target = DocTarget.RESOURCE),
            Description(title = "Описание возвращаемых кодов",
                    value = Documentation.logoutResponseValue,
                    target = DocTarget.RESPONSE),
            Description(title = "Пример возвращаемых данных",
                    value = Documentation.agentJsonReturnValue,
                    target = DocTarget.RETURN)
    )
    @Throws(Exception::class)
    fun logout(): Response
}
