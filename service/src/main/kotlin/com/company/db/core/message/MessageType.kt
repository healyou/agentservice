package com.company.db.core.message

import com.company.db.base.Codable
import com.company.db.base.Entity

/**
 * Тип сообщения - зависит от цели сообщения
 *
 * @author Nikita Gorodilov
 */
class MessageType (
        override var id: Long?,
        var code: Code,
        var name: String,
        var messageOrder: Long,
        var messageGoalType: MessageGoalType,
        var isDeleted: Boolean
): Entity {

    // todo для каждого MessageGoalType.Code тут сделать свой codable
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