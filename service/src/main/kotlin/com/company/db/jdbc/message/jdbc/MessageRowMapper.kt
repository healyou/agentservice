package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.base.Codable
import com.company.db.base.toIsDeleted
import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentType
import com.company.db.core.message.Message
import com.company.db.core.message.MessageBodyType
import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageType
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Создание объекта Message из ResultSet запроса базы данных
 *
 * @author Nikita Gorodilov
 */
class MessageRowMapper : AbstractRowMapper<Message>() {

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, i: Int): Message {
        return Message(
                getLong(rs, "id"),
                mapSender(rs),
                arrayListOf(),
                mapMessageType(rs),
                getDate(rs, "create_date"),
                mapBodyType(rs),
                getString(rs, "body")
        )
    }

    private fun mapMessageType(rs: ResultSet): MessageType {
        return MessageType(
                getLong(rs, "message_type_id"),
                Codable.find(MessageType.Code::class.java, getString(rs, "message_type_code")),
                getString(rs, "message_type_name"),
                getLong(rs, "message_type_message_order"),
                mapGoalTypeOnMessageType(rs),
                getString(rs, "message_type_is_deleted").toIsDeleted()
        )
    }

    private fun mapGoalTypeOnMessageType(rs: ResultSet): MessageGoalType {
        return MessageGoalType(
                getLong(rs, "message_type_message_goal_type_id"),
                Codable.find(MessageGoalType.Code::class.java, getString(rs, "message_type_message_goal_type_code")),
                getString(rs, "message_type_message_goal_type_name"),
                getString(rs, "message_type_message_goal_type_is_deleted").toIsDeleted()
        )
    }

    private fun mapBodyType(rs: ResultSet): MessageBodyType {
        return MessageBodyType(
                getLong(rs, "body_type_id"),
                Codable.find(MessageBodyType.Code::class.java, getString(rs, "message_body_type_code")),
                getString(rs, "message_body_type_name"),
                getString(rs, "message_body_type_is_deleted").toIsDeleted()
        )
    }

    private fun mapSender(rs: ResultSet): Agent {
        return Agent(
                getLong(rs, "sender_id"),
                getString(rs, "sender_mas_id"),
                getString(rs, "sender_name"),
                mapAgentType(rs),
                getDate(rs, "sender_create_date"),
                getString(rs, "sender_is_deleted").toIsDeleted()
        )
    }

    private fun mapAgentType(rs: ResultSet): AgentType {
        return AgentType(
                getLong(rs, "sender_type_id"),
                Codable.find(AgentType.Code::class.java, getString(rs, "sender_type_code")),
                getString(rs, "sender_type_name"),
                getString(rs, "sender_type_is_deleted").toIsDeleted()
        )
    }
}