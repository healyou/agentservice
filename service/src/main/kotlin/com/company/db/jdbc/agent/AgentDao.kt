package com.company.db.jdbc.agent

import com.company.db.core.agent.Agent

/**
 * @author Nikita Gorodilov
 */
interface AgentDao {

    fun create(agent: Agent): Long
    fun update(agent: Agent): Long
    fun delete(id: Long)
    fun get(): List<Agent>
    fun get(id: Long): Agent
    fun getByMasId(masId: String): Agent
}