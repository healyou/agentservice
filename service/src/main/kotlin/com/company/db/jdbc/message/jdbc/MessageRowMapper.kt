package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.core.message.Message
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
        TODO("not implemented")
    }
}