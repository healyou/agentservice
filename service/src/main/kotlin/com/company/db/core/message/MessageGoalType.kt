package com.company.db.core.message

import com.company.db.base.Codable
import com.company.db.base.IDictionary

/**
 * Тип цели сообщения
 *
 * @author Nikita Gorodilov
 */
class MessageGoalType (
        override var id: Long?,
        override val code: Code,
        override val name: String,
        override val isDeleted: Boolean
): IDictionary<MessageGoalType.Code> {

    /* Типы тела сообщения */
    enum class Code(override val code: String): Codable<String> {
        TASK_DECISION("task_decision"),
        TEST_MESSAGE_GOAL_TYPE_1("test_message_goal_type_1");
    }
}