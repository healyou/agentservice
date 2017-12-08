package com.company.db.core.agent

import com.company.db.base.Entity
import java.util.*

/**
 * Агент
 *
 * @author Nikita Gorodilov
 */
class Agent(
        /* Идентификатор в бд */
        override var id: Long?,
        /* Уникальный идентификатор агента в многоагентной системе */
        var masId: String,
        /* Имя */
        var name: String,
        /* Тип */
        var type: AgentType,
        /* Дата создания */
        var createDate: Date,
        /* Удалён ли */
        var isDeleted: Boolean //TODO - надо ли вообще это учитывать?
): Entity