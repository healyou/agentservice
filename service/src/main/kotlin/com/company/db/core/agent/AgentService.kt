package com.company.db.core.agent

/**
 * @author Nikita Gorodilov
 */
interface AgentService {

    fun create(agent: Agent)
    fun update(agent: Agent)
    fun delete(id: Long)
    fun get(): List<Agent>
    fun get(id: Long): Agent
}