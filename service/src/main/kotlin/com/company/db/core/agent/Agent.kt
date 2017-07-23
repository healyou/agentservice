package com.company.db.core.agent

import com.company.db.base.Entity
import java.util.*

/**
 * Агент
 *
 * @author Nikita Gorodilov
 */
class Agent(
        override val id: Long,
        val masId: String,
        val name: String,
        val type: AgentType,
        val createDate: Date,
        val isDeleted: Boolean
): Entity