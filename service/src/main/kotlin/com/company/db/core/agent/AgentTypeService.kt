package com.company.db.core.agent

import com.company.db.core.agent.AgentType.Code

/**
 * @author Nikita Gorodilov
 */
interface AgentTypeService {

    fun get(): List<AgentType>

    fun get(code: Code): AgentType
}