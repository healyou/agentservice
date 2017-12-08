package com.company.db.jdbc.message.jdbc

import com.company.db.base.AbstractDao
import com.company.db.base.Entity
import com.company.db.base.toSqlite
import com.company.db.core.agent.Agent
import com.company.db.core.message.Message
import com.company.db.core.message.MessageRecipient
import com.company.db.core.message.MessageRecipientService
import com.company.db.core.sc.MessageSC
import com.company.db.jdbc.agent.jdbc.AgentRowMapper
import com.company.db.jdbc.message.MessageDao
import com.company.rest.Utils
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
        val messageId = getLastInsertId("message")

        // сохранение получателей
        message.recipients.forEach {
            recipientService.create(
                    messageId,
                    MessageRecipient(null, it.recipient, null)
            )
        }

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
                    MessageRecipient(null, it.recipient, it.viewedDate)
            )
        }

        return message.id!!
    }

    override fun delete(id: Long) {
        jdbcTemplate.update("delete from message_v where id = ?;", id)
    }

    override fun get(sc: MessageSC): List<Message> {
        val sql = StringBuilder("select * from message_v ")

        /* Конфигурация поискового запроса */
        applyCondition(sql, sc)
        /* сообщения */
        val messages = query(sql.toString(), MessageRowMapper())
        /* получаем получателей сообщения */
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

        return message
    }

    override fun use(message: Message) {
        if (message.recipients.isNotEmpty()) {
            jdbcTemplate.update(
                    "update message_recipient set viewed_date = strftime('%Y-%m-%d %H:%M:%f') " +
                            " where message_id = ? and " +
                            " recipient_id in ${configureInQuery(message.recipients.map { it.recipient })}",
                    message.id!!
            )
        }
    }

    /* Делаем поисковых запрос */
    private fun applyCondition(sql: StringBuilder, sc: MessageSC) {
        val addSqlList = arrayListOf<String>()

        // TODO -> QueryBuilder
        /* параметры запроса */
        if (Utils.isOneNotNull(
                sc.isViewed,
                sc.type,
                sc.goalType,
                sc.senderId,
                sc.bodyType,
                sc.sinceCreatedDate,
                sc.sinceViewedDate,
                sc.recipientAgentId
        )) {
            sql.append("where ")
        }
        if (sc.isViewed != null) {
            var nullQuery = ""
            if (sc.isViewed!!) {
                nullQuery = "is not null"
            }
            else {
                nullQuery = "is null"
            }
            addSqlList.add(" exists (select 1 from message_recipient_v mrv " +
                    "where mrv.message_id = message_v.id " +
                    "and mrv.viewed_date $nullQuery " +
                    "${if (sc.recipientAgentId != null) "and mrv.recipient_id = ${sc.recipientAgentId}" else ""}) ")
        }
        if (sc.senderId != null) {
            addSqlList.add(" sender_id = ${sc.senderId} ")
        }
        if (sc.bodyType != null) {
            // todo при поиске по коду надо приводить всё к одному регистру(так будет удобнее)
            addSqlList.add(" message_body_type_code = '${sc.bodyType}'")
        }
        if (sc.goalType != null) {
            addSqlList.add(" message_goal_type_code = '${sc.goalType}'")
        }
        if (sc.type != null) {
            addSqlList.add(" message_type_code = '${sc.type}'")
        }
        if (sc.sinceCreatedDate != null) {
            addSqlList.add(" create_date >= '${sc.sinceCreatedDate!!.toSqlite()}'")
        }
        if (sc.sinceViewedDate != null) {
            addSqlList.add(" exists (select 1 from message_recipient_v mrv where mrv.message_id = message_v.id and mrv.viewed_date >= '${sc.sinceViewedDate!!.toSqlite()}') ")
        }
        if (sc.recipientAgentId != null) {
            addSqlList.add(" exists (select 1 from message_recipient_v mrv where mrv.message_id = message_v.id and mrv.recipient_id = ${sc.recipientAgentId}) ")
        }

        /* объединяем условия запроса */
        for (i in addSqlList.indices) {
            sql.append(addSqlList[i])
            if (i != addSqlList.size - 1) {
                sql.append(" and ")
            }
        }
    }
}