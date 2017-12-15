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
        override val code: String,
        override val name: String,
        override val isDeleted: Boolean
): IDictionary