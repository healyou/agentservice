package com.company.db.core.agent

import com.company.db.core.sc.AgentSC

/**
 * @author Nikita Gorodilov
 */
interface AgentService {

    fun create(agent: Agent): Long
    fun update(agent: Agent): Long
    fun delete(id: Long)
    fun get(agentSC: AgentSC): List<Agent>
    fun get(id: Long): Agent
    fun getByMasId(masId: String): Agent
}