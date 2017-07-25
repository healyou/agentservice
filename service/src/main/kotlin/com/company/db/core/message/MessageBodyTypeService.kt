package com.company.db.core.message

/**
 * @author Nikita Gorodilov
 */
interface MessageBodyTypeService {

    fun get(): List<MessageBodyType>

    fun get(code: MessageBodyType.Code): MessageBodyType
}