package com.company.db.jdbc.message

import com.company.db.core.message.Message
import com.company.db.core.message.MessageService
import com.company.db.core.sc.MessageSC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Сервис работы с сообщениями
 *      Грузит всё, что связано с сообщениями
 *      Все типы данных + таблицу получателей
 *
 * @author Nikita Gorodilov
 */
@Component
open class MessageServiceImpl : MessageService{

    @Autowired
    private lateinit var dao: MessageDao

    /* Создание сообщения */
    override fun create(message: Message): Long {
        return dao.create(message)
    }

    /* Обновление сообщения */
    override fun update(message: Message): Long {
        return dao.update(message)
    }

    /* Удаление сообщения */
    override fun delete(id: Long) {
        dao.delete(id)
    }

    /* Получить все сообщения с фильтром */
    override fun get(sc: MessageSC): List<Message> {
        return dao.get(sc)
    }

    /* Получить сообщение */
    override fun get(id: Long): Message {
        return dao.get(id)
    }
}