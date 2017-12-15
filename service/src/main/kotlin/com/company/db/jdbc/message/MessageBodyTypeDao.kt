package com.company.db.jdbc.message

import com.company.db.core.message.MessageBodyType

/**
 * @author Nikita Gorodilov
 */
interface MessageBodyTypeDao {

    fun get(): List<MessageBodyType>

    fun getByCode(code: String): MessageBodyType
}