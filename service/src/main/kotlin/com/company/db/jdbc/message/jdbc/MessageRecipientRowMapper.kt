package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.base.Codable
import com.company.db.base.toIsDeleted
import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentType
import com.company.db.core.message.MessageRecipient
import java.sql.ResultSet

/**
 * @author Nikita Gorodilov
 */
class MessageRecipientRowMapper : AbstractRowMapper<MessageRecipient>() {

    override fun mapRow(rs: ResultSet, index: Int): MessageRecipient {
        return MessageRecipient(
                getLong(rs, "id"),
                mapRecipient(rs)
        )
    }

    private fun mapRecipient(rs: ResultSet): Agent {
        return Agent(
                getLong(rs, "recipient_id"),
                getString(rs, "recipient_mas_id"),
                getString(rs, "recipient_name"),
                mapAgentType(rs),
                getDate(rs, "recipient_create_date"),
                getString(rs, "recipient_is_deleted").toIsDeleted()
        )
    }

    private fun mapAgentType(rs: ResultSet): AgentType {
        return AgentType(
                getLong(rs, "recipient_type_id"),
                Codable.find(AgentType.Code::class.java,getString(rs, "recipient_type_code")),
                getString(rs, "recipient_type_name"),
                getString(rs,"recipient_type_is_deleted").toIsDeleted()
        )
    }
}