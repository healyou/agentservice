package com.company.db.core.agent

/**
 * @author Nikita Gorodilov
 */
interface AgentTypeService {

    fun get(): List<AgentType>

    fun getByCode(code: String): AgentType
}