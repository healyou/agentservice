package com.company.db.jdbc.message

import com.company.db.core.message.MessageGoalType

/**
 * @author Nikita Gorodilov
 */
interface MessageGoalTypeDao {

    fun get(): List<MessageGoalType>

    fun getByCode(code: String): MessageGoalType
}