package com.company.db.core.message

/**
 * @author Nikita Gorodilov
 */
interface MessageTypeService {

    /**
     * Получаем список типов сообщения, связанных с выбранным MessageGoalType
     */
    fun get(messageGoalType: MessageGoalType): List<MessageType>

    fun get(code: MessageType.Code): MessageType
}