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
        var recipients: List<Agent>,
        /* Цель сообщения */
        var goalType: MessageGoalType,
        /* Цель сообщения */
        var type: MessageType,
        /* Дата создания */
        var createDate: Date,
        /* Цель сообщения */
        var viewedDate: Date?, // todo вынести в таблицу message_recipient
        /* Просмотрено ли данное сообщение */
        var isViewed: Boolean, // todo вынести в таблицу message_recipient
        /* Тип тела сообщения */
        var bodyType: MessageBodyType,
        /* Тело сообщения */
        var body: String
): Entity

// todo получатели сообщения