package com.company.db.jdbc.agent

import com.company.db.core.agent.Agent

/**
 * @author Nikita Gorodilov
 */
interface AgentDao {

    fun create(agent: Agent)
    fun update(agent: Agent)
    fun delete(id: Long)
    fun get(): List<Agent>
    fun get(id: Long): Agent
}