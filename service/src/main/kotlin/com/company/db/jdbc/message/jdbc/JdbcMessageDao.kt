package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractDao
import com.company.db.base.toSqlite
import com.company.db.core.agent.Agent
import com.company.db.core.message.Message
import com.company.db.jdbc.agent.jdbc.AgentRowMapper
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
        jdbcTemplate.update("insert into message_v (sender_id, message_goal_type_id, message_type_id, body_type_id, body) values (?, ?, ?, ?, ?)",
                message.sender.id!!,
                message.goalType.id!!,
                message.type.id!!,
                message.bodyType.id!!,
                message.body
        )

        /* id последней введённой записи */
        return jdbcTemplate.queryForObject("select seq from sqlite_sequence where name='message';", Long::class.java)
    }

    override fun update(message: Message): Long {
        jdbcTemplate.update("update message_v SET sender_id=?,message_goal_type_id=?,message_type_id=?,viewed_date=?,is_viewed=?,body_type_id=?,body=? where id = ?",
                message.sender.id!!,
                message.goalType.id!!,
                message.type.id!!,
                message.viewedDate?.toSqlite(),
                if (message.isViewed) "Y" else "N",
                message.bodyType.id!!,
                message.body,
                message.id
        )

        return message.id!!
    }

    override fun delete(id: Long) {
        // todo вынести в AbstractDao
        jdbcTemplate.update("delete from message_v where id = ?;", id)
    }

    override fun get(): List<Message> {
        val messages = query("select * from message_v", MessageRowMapper())

        messages.forEach {
            it.recipients = getRecipitients(it.id!!)
        }
        return messages
    }

    override fun get(id: Long): Message {
        return jdbcTemplate.queryForObject(
                "select * from message_v where id = ?",
                MessageRowMapper(),
                id
        )
    }

    // todo закоментить
    private fun getRecipitients(messageId: Long): List<Agent> {
        /**
        SELECT * from agent_v a
        LEFT JOIN message_recipient mr on a.id = mr.recipient_id
        --LEFT JOIN message_v m on m.id = mr.message_id
        where mr.message_id = 1;

        запрос выберет всех агентов для сообщения -> его отдельно запарсить
         */
        return jdbcTemplate.query("SELECT * from agent_v a\n" +
                "  LEFT JOIN message_recipient mr on a.id = mr.recipient_id\n" +
                "  --LEFT JOIN message_v m on m.id = mr.message_id\n" +
                "  where mr.message_id = ?",
                AgentRowMapper(),
                messageId
        )
    }
}