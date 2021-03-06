package com.company.db.core.message

import com.company.db.base.Entity
import com.company.db.core.agent.Agent
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*

/**
 * Получатель сообщения - привязан к сущности Message
 *
 * @author Nikita Gorodilov
 */
class MessageRecipient (
        /* Идентификатор записи */
        override var id: Long?,
        /* Получатель сообщения */
        var recipient: Agent,
        /* Дата просмотра пользователем */
        var viewedDate: Date?
): Entity {

    /* Просмотрено ли сообщение */
    val isViewed
        @JsonIgnore
        get() = viewedDate != null
}