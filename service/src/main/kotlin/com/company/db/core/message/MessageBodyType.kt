package com.company.db.core.message

import com.company.db.base.IDictionary

/**
 * Тип тела сообщения
 *
 * @author Nikita Gorodilov
 */
class MessageBodyType (
        override var id: Long?,
        override val code: String,
        override val name: String,
        override val isDeleted: Boolean
): IDictionary