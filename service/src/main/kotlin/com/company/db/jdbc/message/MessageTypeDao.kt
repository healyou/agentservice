package com.company.db.jdbc.message

import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageType

/**
 * @author Nikita Gorodilov
 */
interface MessageTypeDao {

    fun get(messageGoalType: MessageGoalType): List<MessageType>

    fun get(): List<MessageType>

    fun getByCode(code: String): MessageType
}