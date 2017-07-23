package com.company.db.jdbc.agent.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.base.Codable
import com.company.db.core.agent.AgentType;
import com.company.db.core.agent.AgentType.Code;
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
                Codable.find(Code::class.java, rs.getString("code")),
                getString(rs,"name"),
                rs.getString("is_deleted") != "N"
        )
    }
}