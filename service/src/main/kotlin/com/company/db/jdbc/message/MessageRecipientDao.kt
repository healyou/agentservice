package com.company.db.jdbc.message

import com.company.db.core.agent.Agent
import com.company.db.core.message.Message
import com.company.db.core.message.MessageRecipient

/**
 * @author Nikita Gorodilov
 */
interface MessageRecipientDao {

    fun get(message: Message): List<MessageRecipient>
    fun getById(id: Long): MessageRecipient
    fun getByRecipientId(recipientId: Long): List<MessageRecipient>
    fun update(messageRecipient: MessageRecipient)
    fun create(messageId: Long, messageRecipient: MessageRecipient): Long
    fun delete(messageRecipient: MessageRecipient)
    fun delete(message: Message)
    fun use(recipient: Agent)
    fun use(messages: List<Message>, recipient: Agent)
}