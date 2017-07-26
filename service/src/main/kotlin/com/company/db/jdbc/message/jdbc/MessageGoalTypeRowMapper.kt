package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.base.Codable
import com.company.db.base.toIsDeleted
import com.company.db.core.message.MessageGoalType
import java.sql.ResultSet
import java.sql.SQLException

/**
 * @author Nikita Gorodilov
 */
class MessageGoalTypeRowMapper : AbstractRowMapper<MessageGoalType>() {

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, i: Int): MessageGoalType {
        return MessageGoalType(
                getLong(rs,"id"),
                Codable.find(MessageGoalType.Code::class.java, rs.getString("code")),
                getString(rs,"name"),
                rs.getString("is_deleted").toIsDeleted()
        )
    }
}