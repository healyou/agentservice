package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractDao
import com.company.db.core.message.Message
import com.company.db.core.message.MessageRecipient
import com.company.db.jdbc.message.MessageRecipientDao
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class JdbcMessageRecipientsDao: AbstractDao(), MessageRecipientDao {

    override fun get(message: Message): List<MessageRecipient> {
        // todo в abstract
        return jdbcTemplate.query("select * from message_recipient_v where message_id = ?", MessageRecipientRowMapper(), message.id!!)
    }

    override fun getById(id: Long): MessageRecipient {
        return jdbcTemplate.queryForObject(
                "select * from message_recipient_v where id = ?",
                MessageRecipientRowMapper(),
                id
        )
    }

    override fun getByRecipientId(recipientId: Long): List<MessageRecipient> {
        return jdbcTemplate.query(
                "select * from message_recipient_v where recipient_id = ?",
                MessageRecipientRowMapper(),
                recipientId
        )
    }

    override fun create(messageId: Long, messageRecipient: MessageRecipient): Long {
        /* todo почему то не работает метод в AbstractDao */
        jdbcTemplate.update(
                "insert into message_recipient (message_id, recipient_id) values (?, ?)",
                messageId,
                messageRecipient.recipient.id!!
        )

        /* id последней введённой записи */
        return jdbcTemplate.queryForObject("select seq from sqlite_sequence where name='message_recipient';", Long::class.java)
    }

    override fun delete(messageRecipient: MessageRecipient) {
        jdbcTemplate.update(
                "delete from message_recipient where id = ?",
                messageRecipient.id!!
        )
    }

    override fun delete(message: Message) {
        jdbcTemplate.update(
                "delete from message_recipient where message_id = ?",
                message.id!!
        )
    }
}