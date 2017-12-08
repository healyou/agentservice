package com.company.db.core.message

import com.company.db.base.Codable
import com.company.db.base.IDictionary

/**
 * Тип тела сообщения
 *
 * @author Nikita Gorodilov
 */
class MessageBodyType (
        override var id: Long?,
        override val code: Code,
        override val name: String,
        override val isDeleted: Boolean
): IDictionary<MessageBodyType.Code> {

    /* Типы тела сообщения */
    enum class Code(override val code: String): Codable<String> {
        JSON("json");
    }
}