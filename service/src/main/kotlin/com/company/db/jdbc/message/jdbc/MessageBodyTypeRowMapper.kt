package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractRowMapper
import com.company.db.base.Codable
import com.company.db.base.toIsDeleted
import com.company.db.core.message.MessageBodyType
import com.company.db.core.message.MessageBodyType.Code
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
                Codable.find(Code::class.java, rs.getString("code")),
                getString(rs,"name"),
                rs.getString("is_deleted").toIsDeleted()
        )
    }
}