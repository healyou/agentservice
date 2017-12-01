package com.company.db.jdbc.message

import com.company.db.core.agent.Agent
import com.company.db.core.message.Message
import com.company.db.core.sc.MessageSC

/**
 * @author Nikita Gorodilov
 */
interface MessageDao {

    fun create(message: Message): Long

    fun update(message: Message): Long

    fun delete(id: Long)

    fun get(sc: MessageSC): List<Message>

    fun get(id: Long): Message

    fun use(message: Message)
}