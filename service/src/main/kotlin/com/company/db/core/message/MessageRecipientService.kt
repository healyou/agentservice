package com.company.db.core.message

/**
 * @author Nikita Gorodilov
 */
interface MessageRecipientService {

    fun get(message: Message): List<MessageRecipient>

    fun get(id: Long): MessageRecipient

    fun create(messageId: Long, messageRecipient: MessageRecipient): Long

    fun delete(messageRecipient: MessageRecipient)

    fun delete(message: Message)
}