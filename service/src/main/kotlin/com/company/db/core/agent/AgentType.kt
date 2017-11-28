package com.company.db.core.agent

import com.company.db.base.Codable
import com.company.db.base.Entity

/**
 * Тип агента
 *
 * @author Nikita Gorodilov
 */
class AgentType(
        override var id: Long?,
        var code: Code,
        var name: String,
        var isDeleted: Boolean
): Entity {

    /* Типы агентов */
    enum class Code(override val code: String): Codable<String> {
        WORKER("worker"),
        SERVER("server"),
        TEST_AGENT_TYPE_1("test_agent_type_1"),
        TEST_AGENT_TYPE_2("test_agent_type_2");
    }
}