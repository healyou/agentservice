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
        override val code: Code,
        override val name: String,
        var messageOrder: Long,
        var messageGoalType: MessageGoalType,
        override val isDeleted: Boolean
): IDictionary<MessageType.Code> {

    /* Типы сообщения */
    enum class Code(override val code: String): Codable<String> {
        /* Связанные с MessageGoalType.Code.TASK_DECISION */
        SEARCH_TASK_SOLUTION("search_task_solution"),
        SEARCH_SOLUTION("search_solution"),
        SOLUTION_ANSWER("solution_answer"),
        TASK_SOLUTION_ANSWER("task_solution_answer");

        /* Связанные с ... */
    }
}