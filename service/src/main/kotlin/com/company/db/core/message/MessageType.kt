package com.company.db.core.message

import com.company.db.base.Codable
import com.company.db.base.IDictionary

/**
 * Тип сообщения - зависит от цели сообщения
 *
 * @author Nikita Gorodilov
 */
class MessageType (
        override var id: Long?,
        override val code: String,
        override val name: String,
        var order: Long,
        var goalType: MessageGoalType,
        override val isDeleted: Boolean
): IDictionary