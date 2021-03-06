package com.company.db.core.message

/**
 * @author Nikita Gorodilov
 */
interface MessageGoalTypeService {

    fun get(): List<MessageGoalType>

    fun getByCode(code: String): MessageGoalType
}