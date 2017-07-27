package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractDao
import com.company.db.core.message.MessageGoalType
import com.company.db.jdbc.message.MessageGoalTypeDao
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class JdbcMessageGoalTypeDao: AbstractDao(), MessageGoalTypeDao {

    override fun get(): List<MessageGoalType> {
        return query("select * from message_goal_type", MessageGoalTypeRowMapper())
    }

    override fun get(code: MessageGoalType.Code): MessageGoalType {
        // todo исправить вызов на AbstractDao.queryForObject -> почему то не работал
        return jdbcTemplate.queryForObject(
                "select * from message_goal_type where code = ?",  
                MessageGoalTypeRowMapper(), 
                code.code
        )
    }
}