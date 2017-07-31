package com.company.db.core.sc

import java.util.*

/**
 * Параметры для поиска сообщений в базе данных
 *
 * @author Nikita Gorodilov
 */
class MessageSC {

    /* Просмотрено ли сообщение */
    var isViewed: Boolean? = null
    /* Отправетель(Agent) */
    var senderId: Long? = null
    /* Тип тела сообщения(MessageBodyType.code) */
    var bodyType: String? = null
    /* Тип цели сообщения(MessageGoalType.code) */
    var goalType: String? = null
    /* Тип цели сообщения(MessageType.code) */
    var type: String? = null

    // todo надо ли?
    /* С даты создания сообщения и больше */
    //var sinceCreatedDate: Date? = null
    /* С даты просмотра сообщения и больше */
    //var sinceViewedDate: Date? = null
}