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
        // todo исправить - вынести в AbstractDao
        return jdbcTemplate.query(
                "select * from message_type_v where message_goal_type_id = ?",
                MessageTypeRowMapper(),
                messageGoalType.id!!
        )
    }

    override fun get(code: MessageType.Code): MessageType {
        // todo исправить вызов на AbstractDao.queryForObject -> почему то не работал
        return jdbcTemplate.queryForObject("select * from message_type_v where code = ?",  MessageTypeRowMapper(), code.code)
    }
}