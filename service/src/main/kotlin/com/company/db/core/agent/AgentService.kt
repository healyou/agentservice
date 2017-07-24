package com.company.db.core.agent

/**
 * @author Nikita Gorodilov
 */
interface AgentService {

    fun create(agent: Agent): Long
    fun update(agent: Agent): Long
    fun delete(id: Long)
    fun get(): List<Agent>
    fun get(id: Long): Agent
}