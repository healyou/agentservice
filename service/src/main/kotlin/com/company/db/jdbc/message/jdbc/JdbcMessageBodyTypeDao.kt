package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractDao
import com.company.db.core.message.MessageBodyType
import com.company.db.jdbc.message.MessageBodyTypeDao
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class JdbcMessageBodyTypeDao: AbstractDao(), MessageBodyTypeDao {

    override fun get(): List<MessageBodyType> {
        return query("select * from message_body_type", MessageBodyTypeRowMapper())
    }

    override fun get(code: MessageBodyType.Code): MessageBodyType {
        return jdbcTemplate.queryForObject("select * from message_body_type where code = ?",  MessageBodyTypeRowMapper(), code.code)
    }
}