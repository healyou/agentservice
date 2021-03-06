package com.company.db.jdbc.agent.jdbc

import com.company.db.base.AbstractDao
import com.company.db.base.toSqlite
import com.company.db.core.agent.Agent
import com.company.db.core.sc.AgentSC
import com.company.db.jdbc.agent.AgentDao
import com.company.rest.utils.Utils
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class JdbcAgentDao: AbstractDao(), AgentDao {

    override fun create(agent: Agent): Long {
        jdbcTemplate.update("INSERT INTO agent " +
                "(mas_id, name, type_id, create_date, is_deleted) VALUES (?, ?, ?, ?, ?);",
                agent.masId,
                agent.name,
                agent.type.id,
                agent.createDate.toSqlite(),
                agent.isDeleted.toSqlite()
        )

        return getSequence("agent")
    }

    override fun update(agent: Agent): Long {
        jdbcTemplate.update("update agent SET mas_id=?,name=?,type_id=?,create_date=?,is_deleted=? where id = ?;",
                agent.masId,
                agent.name,
                agent.type.id,
                agent.createDate.toSqlite(),
                agent.isDeleted.toSqlite(),
                agent.id!!
        )

        return agent.id!!
    }

    override fun delete(id: Long) {
        jdbcTemplate.update("delete from agent where id = ?;", id)
    }

    override fun get(agentSC: AgentSC): List<Agent> {
        val sql = StringBuilder("select * from agent_v ")

        /* Конфигурация поискового запроса */
        applyCondition(sql, agentSC)

        return query(sql.toString(), AgentRowMapper())
    }

    override fun get(id: Long): Agent {
        return jdbcTemplate.queryForObject("select * from agent_v where id = ?", AgentRowMapper(), id)
    }

    override fun getByMasId(masId: String): Agent {
        return jdbcTemplate.queryForObject("select * from agent_v where mas_id = ?", AgentRowMapper(), masId)
    }

    override fun isExistsAgent(masId: String): Boolean {
        return jdbcTemplate.queryForObject(
                "SELECT EXISTS (SELECT 1 FROM agent WHERE mas_id = ?)",
                Boolean::class.java,
                masId
        )
    }

    /* Делаем поисковых запрос */
    private fun applyCondition(sql: StringBuilder, sc: AgentSC) {
        val addSqlList = arrayListOf<String>()

        // TODO -> QueryBuilder
        /* параметры запроса */
        if (Utils.isOneNotNull(sc.type, sc.isDeleted, sc.name)) {
            sql.append("where ")
        }
        if (sc.type != null) {
            addSqlList.add(" type_code = '${sc.type}'")
        }
        if (sc.isDeleted != null) {
            addSqlList.add(" is_deleted = '${sc.isDeleted!!.toSqlite()}'")
        }
        if (sc.name != null) {
            addSqlList.add(" UPPER(name) = UPPER('${sc.name}')")
        }

        /* объединяем условия запроса */
        for (i in addSqlList.indices) {
            sql.append(addSqlList[i])
            if (i != addSqlList.size - 1) {
                sql.append(" and ")
            }
        }
    }
}