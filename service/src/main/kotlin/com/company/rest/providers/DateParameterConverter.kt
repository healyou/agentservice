package com.company.rest.providers

import com.company.db.base.fromSqlite
import com.company.db.base.toSqlite
import java.util.*
import javax.ws.rs.WebApplicationException
import javax.ws.rs.ext.ParamConverter

/**
 * Конвертация даты, приходящих на сервер
 *
 * @author Nikita Gorodilov
 */
class DateParameterConverter : ParamConverter<Date> {

    override fun fromString(string: String): Date {
        try {
            return string.fromSqlite()

        } catch (e: Exception) {
            throw WebApplicationException(e)
        }
    }

    override fun toString(date: Date): String {
        return date.toSqlite()
    }
}