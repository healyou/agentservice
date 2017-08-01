package com.company.db.core.sc

/**
 * Параметры для поиска агентов в базе данных
 *
 * @author Nikita Gorodilov
 */
class AgentSC {

    /* Тип агента(AgentType.Code.code) */
    var type: String? = null
    /* Удалена ли запись агента */
    var isDeleted: Boolean? = null
}