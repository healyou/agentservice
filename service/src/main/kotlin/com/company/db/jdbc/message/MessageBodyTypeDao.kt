package com.company.db.jdbc.message

import com.company.db.core.message.MessageBodyType

/**
 * @author Nikita Gorodilov
 */
interface MessageBodyTypeDao {

    fun get(): List<MessageBodyType>

    fun get(code: MessageBodyType.Code): MessageBodyType
}