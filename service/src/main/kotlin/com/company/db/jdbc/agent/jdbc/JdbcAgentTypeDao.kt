package com.company.db.jdbc.agent.jdbc

import com.company.db.base.AbstractDao
import com.company.db.core.agent.AgentType
import com.company.db.jdbc.agent.AgentTypeDao
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class JdbcAgentTypeDao: AbstractDao(), AgentTypeDao{

    override fun get(): List<AgentType> {
        return query("select * from agent_type", AgentTypeRowMapper())
    }

    override fun get(code: AgentType.Code): AgentType {
        // todo исправить вызов на AbstractDao.queryForObject -> почему то не работал
        return jdbcTemplate.queryForObject("select * from agent_type where code = ?",  AgentTypeRowMapper(), code.code)
    }
}