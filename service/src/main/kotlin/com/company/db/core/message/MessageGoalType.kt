package com.company.db.core.message

import com.company.db.base.Codable
import com.company.db.base.Entity

/**
 * Тип цели сообщения
 *
 * @author Nikita Gorodilov
 */
class MessageGoalType (
        override var id: Long?,
        var code: Code,
        var name: String,
        var isDeleted: Boolean
): Entity {

    /* Типы тела сообщения */
    enum class Code(override val code: String): Codable<String> {
        TASK_DECISION("task_decision");
    }
}