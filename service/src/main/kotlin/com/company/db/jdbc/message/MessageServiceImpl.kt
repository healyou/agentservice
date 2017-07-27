package com.company.db.jdbc.message

import com.company.db.core.message.Message
import com.company.db.core.message.MessageService
import org.springframework.stereotype.Component

/**
 * Сервис работы с сообщениями
 *
 * @author Nikita Gorodilov
 */
@Component
open class MessageServiceImpl : MessageService{

    /* Создание сообщения */
    override fun create(message: Message): Long {
        TODO("not implemented")
    }

    /* Обновление сообщения */
    override fun update(message: Message): Long {
        TODO("not implemented")
    }

    /* Удаление сообщения */
    override fun delete(id: Long) {
        TODO("not implemented")
    }

    /* Получить все сообщения */
    override fun get(): List<Message> {
        TODO("not implemented")
    }

    /* Получить сообщение */
    override fun get(id: Long): Message {
        TODO("not implemented")
    }
}