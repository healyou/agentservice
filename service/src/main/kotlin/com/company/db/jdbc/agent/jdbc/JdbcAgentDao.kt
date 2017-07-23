package com.company.db.jdbc.agent.jdbc

import com.company.db.base.AbstractDao
import com.company.db.core.agent.Agent
import com.company.db.jdbc.agent.AgentDao
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class JdbcAgentDao: AbstractDao(), AgentDao {

    override fun create(agent: Agent) {

    }

    override fun update(agent: Agent) {

    }

    override fun delete(id: Long) {

    }

    override fun get(): List<Agent> {
        return query("select * from agent_v", AgentRowMapper())
    }

    override fun get(id: Long): Agent {
        // todo исправить вызов на AbstractDao.queryForObject -> почему то не работал
        return jdbcTemplate.queryForObject("select * from agent_v where id = ?", AgentRowMapper(), id)
    }
}