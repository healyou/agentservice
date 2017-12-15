package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractDao
import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageType
import com.company.db.jdbc.message.MessageTypeDao
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class JdbcMessageTypeDao: AbstractDao(), MessageTypeDao {

    override fun get(messageGoalType: MessageGoalType): List<MessageType> {
        return jdbcTemplate.query(
                "select * from message_type_v where message_goal_type_id = ?",
                MessageTypeRowMapper(),
                messageGoalType.id!!
        )
    }

    override fun get(): List<MessageType> {
        return jdbcTemplate.query("select * from message_type_v", MessageTypeRowMapper())
    }

    override fun getByCode(code: String): MessageType {
        return jdbcTemplate.queryForObject(
                "select * from message_type_v where UPPER(code) = UPPER(?)",
                MessageTypeRowMapper(),
                code
        )
    }
}