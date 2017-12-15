package com.company.db.core.message

/**
 * @author Nikita Gorodilov
 */
interface MessageBodyTypeService {

    fun get(): List<MessageBodyType>

    fun getByCode(code: String): MessageBodyType
}