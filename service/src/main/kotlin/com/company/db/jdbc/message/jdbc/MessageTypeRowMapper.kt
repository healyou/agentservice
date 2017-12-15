package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.base.toIsDeleted
import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageType
import java.sql.ResultSet
import java.sql.SQLException

/**
 * @author Nikita Gorodilov
 */
class MessageTypeRowMapper : AbstractRowMapper<MessageType>() {

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, i: Int): MessageType {
        return MessageType(
                getLong(rs,"id"),
                rs.getString("code"),
                getString(rs,"name"),
                getLong(rs, "message_order"),
                mapMessageGoalType(rs),
                rs.getString("is_deleted").toIsDeleted()
        )
    }

    private fun mapMessageGoalType(rs: ResultSet): MessageGoalType {
        return MessageGoalType(
                getLong(rs, "message_goal_type_id"),
                rs.getString("message_goal_type_code"),
                getString(rs, "message_goal_type_name"),
                getString(rs, "message_goal_type_is_deleted").toIsDeleted()
        )
    }
}