package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.base.toIsDeleted
import com.company.db.core.message.MessageBodyType
import java.sql.ResultSet
import java.sql.SQLException

/**
 * @author Nikita Gorodilov
 */
class MessageBodyTypeRowMapper: AbstractRowMapper<MessageBodyType>() {

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, i: Int): MessageBodyType {
        return MessageBodyType(
                getLong(rs,"id"),
                rs.getString("code"),
                getString(rs,"name"),
                rs.getString("is_deleted").toIsDeleted()
        )
    }
}