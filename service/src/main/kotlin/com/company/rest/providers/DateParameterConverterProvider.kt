package com.company.rest.providers

import java.lang.reflect.Type
import java.util.*
import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider

/**
 * Конвертация даты, приходящих на сервер
 *
 * @author Nikita Gorodilov
 */
class DateParameterConverterProvider : ParamConverterProvider {

    @Suppress("UNCHECKED_CAST")
    override fun <T> getConverter(type: Class<T>, type1: Type, antns: Array<Annotation>): ParamConverter<T>? {
        if (Date::class.java == type) {
            return DateParameterConverter() as ParamConverter<T>
        }
        return null
    }
}