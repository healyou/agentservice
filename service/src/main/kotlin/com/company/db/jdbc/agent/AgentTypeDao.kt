package com.company.db.jdbc.agent

import com.company.db.core.agent.AgentType

/**
 * @author Nikita Gorodilov
 */
interface AgentTypeDao {

    fun get(): List<AgentType>

    fun get(code: AgentType.Code): AgentType
}