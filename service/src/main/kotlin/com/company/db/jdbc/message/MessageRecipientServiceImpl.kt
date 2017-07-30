package com.company.db.jdbc.message

import com.company.db.core.message.Message
import com.company.db.core.message.MessageRecipient
import com.company.db.core.message.MessageRecipientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Сервис работы с получателями сообщения
 *
 * @author Nikita Gorodilov
 */
@Component
private open class MessageRecipientServiceImpl : MessageRecipientService  {

    @Autowired
    private lateinit var dao: MessageRecipientDao

    /**
     * Получение всех получателей сообщения
     */
    override fun get(message: Message): List<MessageRecipient> {
        return dao.get(message)
    }

    /**
     * Получение получателя сообщения по id
     */
    override fun getById(id: Long): MessageRecipient {
        return dao.getById(id)
    }

    /**
     * Получение получателя сообщения по id получателя
     */
    override fun getByRecipientId(recipientId: Long): List<MessageRecipient> {
        return dao.getByRecipientId(recipientId)
    }

    override fun update(messageRecipient: MessageRecipient) {
        dao.update(messageRecipient)
    }

    /**
     * Создание получателя сообщения
     */
    override fun create(messageId: Long, messageRecipient: MessageRecipient): Long {
        return dao.create(messageId, messageRecipient)
    }

    /**
     * Удаление получателя сообщения
     */
    override fun delete(messageRecipient: MessageRecipient) {
        dao.delete(messageRecipient)
    }

    /**
     * Удаление всех получателей сообщения
     */
    override fun delete(message: Message) {
        dao.delete(message)
    }
}