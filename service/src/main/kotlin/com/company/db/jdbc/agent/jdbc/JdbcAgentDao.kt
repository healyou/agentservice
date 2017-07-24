package com.company.db.jdbc.agent.jdbc

import com.company.db.base.AbstractDao
import com.company.db.base.toSqlite
import com.company.db.core.agent.Agent
import com.company.db.jdbc.agent.AgentDao
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class JdbcAgentDao: AbstractDao(), AgentDao {

    override fun create(agent: Agent): Long {
        // todo вынести в AbstractDao c возвратом id
        jdbcTemplate.update("INSERT INTO agent " +
                "(mas_id, name, type_id, create_date, is_deleted) VALUES (?, ?, ?, ?, ?);",
                agent.masId,
                agent.name,
                agent.type.id,
                agent.createDate.toSqlite(),
                agent.isDeleted.toSqlite()
        )

        /* id последней введённой записи */
        return jdbcTemplate.queryForObject("select seq from sqlite_sequence where name='agent';", Long::class.java)
    }

    override fun update(agent: Agent): Long {
        // todo вынести в AbstractDao
        jdbcTemplate.update("update agent SET mas_id=?,name=?,type_id=?,create_date=?,is_deleted=? where id = ?;",
                agent.masId,
                agent.name,
                agent.type.id,
                // todo вынести работу с датой и isDeleted
                agent.createDate.toSqlite(),
                agent.isDeleted.toSqlite(),
                agent.id!!
        )

        return agent.id!!
    }

    override fun delete(id: Long) {
        // todo вынести в AbstractDao
        jdbcTemplate.update("delete from agent where id = ?;", id)
    }

    override fun get(): List<Agent> {
        return query("select * from agent_v", AgentRowMapper())
    }

    override fun get(id: Long): Agent {
        // todo исправить вызов на AbstractDao.queryForObject -> почему то не работал
        return jdbcTemplate.queryForObject("select * from agent_v where id = ?", AgentRowMapper(), id)
    }
}