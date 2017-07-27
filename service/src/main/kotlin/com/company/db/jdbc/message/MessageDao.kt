package com.company.db.jdbc.message

import com.company.db.core.message.Message

/**
 * @author Nikita Gorodilov
 */
interface MessageDao {

    fun create(message: Message): Long

    fun update(message: Message): Long

    fun delete(id: Long)

    fun get(): List<Message>

    fun get(id: Long): Message
}