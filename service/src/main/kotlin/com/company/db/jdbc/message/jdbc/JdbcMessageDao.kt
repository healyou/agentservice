package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractDao
import com.company.db.base.toSqlite
import com.company.db.core.agent.Agent
import com.company.db.core.message.Message
import com.company.db.core.message.MessageRecipient
import com.company.db.core.message.MessageRecipientService
import com.company.db.jdbc.agent.jdbc.AgentRowMapper
import com.company.db.jdbc.message.MessageDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Класс осуществляет загрузку данных из бд при работе с MessageService
 *
 * @author Nikita Gorodilov
 */
@Component
open class JdbcMessageDao: AbstractDao(), MessageDao {

    @Autowired
    private lateinit var recipientService: MessageRecipientService

    override fun create(message: Message): Long {
        jdbcTemplate.update("insert into message_v (sender_id, message_goal_type_id, message_type_id, body_type_id, body) values (?, ?, ?, ?, ?)",
                message.sender.id!!,
                message.goalType.id!!,
                message.type.id!!,
                message.bodyType.id!!,
                message.body
        )

        /* id последней введённой записи */
        val messageId = jdbcTemplate.queryForObject("select seq from sqlite_sequence where name='message';", Long::class.java)

        // сохранение получателей
        message.recipients.forEach {
            recipientService.create(
                    messageId,
                    MessageRecipient(null, it.recipient, null)
            )
        }

        //createRecipients(messageId, message.recipients)

        return messageId
    }

    override fun update(message: Message): Long {
        jdbcTemplate.update("update message_v SET sender_id=?,message_goal_type_id=?,message_type_id=?,body_type_id=?,body=? where id = ?",
                message.sender.id!!,
                message.goalType.id!!,
                message.type.id!!,
                message.bodyType.id!!,
                message.body,
                message.id
        )

        // пересохранение получателей
        recipientService.delete(message)
        message.recipients.forEach {
            recipientService.create(
                    message.id!!,
                    MessageRecipient(null, it.recipient, null)
            )
        }
        //deleteRecipients(message.id!!)
        //createRecipients(message.id!!, message.recipients)

        return message.id!!
    }

    override fun delete(id: Long) {
        // todo вынести в AbstractDao
        jdbcTemplate.update("delete from message_v where id = ?;", id)
    }

    override fun get(): List<Message> {
        val messages = query("select * from message_v", MessageRowMapper())

        // получаем получателей сообщения
        messages.forEach { it.recipients = recipientService.get(it) }

        return messages
    }

    override fun get(id: Long): Message {
        val message = jdbcTemplate.queryForObject(
                "select * from message_v where id = ?",
                MessageRowMapper(),
                id
        )

        // получаем получателей сообщения
        message.recipients = recipientService.get(message)
        //message.recipients = getRecipitients(message.id!!)

        return message
    }

    /*
     * Работа с recipients
     */

    /**
     * Удаление всех получателей сообщения
     */
    private fun deleteRecipients(messageId: Long) {
        jdbcTemplate.update("delete from message_recipient where message_id = ?", messageId)
    }

    /**
     * Сохранение всех получателей сообщения
     */
    private fun createRecipients(messageId: Long, recipients: List<Agent>) {
        recipients.forEach {
            jdbcTemplate.update(
                    "insert into message_recipient (message_id, recipient_id) values (?, ?)",
                    messageId,
                    it.id!!
            )
        }
    }

    /**
     * Получение всех получателей сообщения
     */
    private fun getRecipitients(messageId: Long): List<Agent> {
        return jdbcTemplate.query("SELECT * from agent_v a\n" +
                "  LEFT JOIN message_recipient mr on a.id = mr.recipient_id\n" +
                "  where mr.message_id = ?",
                AgentRowMapper(),
                messageId
        )
    }
}