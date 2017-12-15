package com.company.db.jdbc.agent.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.base.toIsDeleted
import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentType
import java.sql.ResultSet
import java.sql.SQLException

/**
 * @author Nikita Gorodilov
 */
class AgentRowMapper: AbstractRowMapper<Agent>() {

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, i: Int): Agent {
        return Agent(
                getLong(rs, "id"),
                getString(rs, "mas_id"),
                getString(rs, "name"),
                mapAgentType(rs),
                getDate(rs,"create_date"),
                getString(rs,"is_deleted").toIsDeleted()
        )
    }

    private fun mapAgentType(rs: ResultSet): AgentType {
        return AgentType(
                getLong(rs, "type_id"),
                getString(rs, "type_code"),
                getString(rs, "type_name"),
                getString(rs,"is_deleted").toIsDeleted()
        )
    }
}