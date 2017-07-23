package com.company.db.core.agent

import com.company.db.base.Codable
import com.company.db.base.Entity

/**
 * @author Nikita Gorodilov
 */
class AgentType(
        override var id: Long,
        var code: Code,
        var name: String,
        var isDeleted: Boolean
): Entity {

    enum class Code(override val code: String): Codable<String> {
        WORKER("worker"),
        SERVER("server");
    }
}