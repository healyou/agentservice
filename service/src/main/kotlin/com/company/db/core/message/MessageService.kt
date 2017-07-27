package com.company.db.core.message

/**
 *
 * @author Nikita Gorodilov
 */
interface MessageService {

    fun create(message: Message): Long

    fun update(message: Message): Long

    fun delete(id: Long)

    fun get(): List<Message>

    fun get(id: Long): Message
}