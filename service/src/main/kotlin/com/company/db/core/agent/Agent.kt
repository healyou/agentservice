package com.company.db.core.agent

import com.company.db.base.Entity
import java.util.*

/**
 * Агент
 *
 * @author Nikita Gorodilov
 */
class Agent(
        override var id: Long,
        var masId: String,
        var name: String,
        var type: AgentType,
        var createDate: Date,
        var isDeleted: Boolean
): Entity