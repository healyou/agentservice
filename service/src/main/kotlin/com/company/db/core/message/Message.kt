package com.company.db.core.message

import com.company.db.base.Entity
import com.company.db.core.agent.Agent
import java.util.*

/**
 * @author Nikita Gorodilov
 */
class Message (
        /* Идентификатор сообщения */
        override var id: Long?,
        /* Отправитель сообщения */
        var sender: Agent,
        /* Получатели сообщения */
        var recipients: List<MessageRecipient>,
        /* Цель сообщения */
        var type: MessageType,
        /* Дата создания */
        var createDate: Date,
        /* Тип тела сообщения */
        var bodyType: MessageBodyType,
        /* Тело сообщения */
        var body: String
): Entity