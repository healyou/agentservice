package com.company.db.jdbc.agent.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.base.toIsDeleted
import com.company.db.core.agent.AgentType
import java.sql.ResultSet
import java.sql.SQLException

/**
 * @author Nikita Gorodilov
 */
class AgentTypeRowMapper: AbstractRowMapper<AgentType>() {

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, i: Int): AgentType {
        return AgentType(
                getLong(rs,"id"),
                rs.getString("code"),
                getString(rs,"name"),
                rs.getString("is_deleted").toIsDeleted()
        )
    }
}