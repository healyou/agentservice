package com.company.db.core.agent

import com.company.db.base.Codable
import com.company.db.base.IDictionary

/**
 * Тип агента
 *
 * @author Nikita Gorodilov
 */
class AgentType(
        override var id: Long?,
        override val code: Code,
        override val name: String,
        override val isDeleted: Boolean
): IDictionary<AgentType.Code> {

    /* Типы агентов */
    enum class Code(override val code: String): Codable<String> {
        WORKER("worker"),
        SERVER("server"),
        TEST_AGENT_TYPE_1("test_agent_type_1"),
        TEST_AGENT_TYPE_2("test_agent_type_2");
    }
}