package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractDao
import com.company.db.core.message.Message
import com.company.db.jdbc.message.MessageDao
import org.springframework.stereotype.Component

/**
 * Класс осуществляет загрузку данных из бд при работе с MessageService
 *
 * @author Nikita Gorodilov
 */
@Component
open class JdbcMessageDao: AbstractDao(), MessageDao {

    override fun create(message: Message): Long {
        TODO("not implemented")
    }

    override fun update(message: Message): Long {
        TODO("not implemented")
    }

    override fun delete(id: Long) {
        TODO("not implemented")
    }

    override fun get(): List<Message> {
        TODO("not implemented")
    }

    override fun get(id: Long): Message {
        TODO("not implemented")
    }
}