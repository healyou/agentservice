package com.company.db.core.message

import com.company.db.core.agent.Agent
import com.company.db.core.sc.MessageSC

/**
 *
 * @author Nikita Gorodilov
 */
interface MessageService {

    fun create(message: Message): Long
    fun update(message: Message): Long
    fun delete(id: Long)
    fun get(sc: MessageSC): List<Message>
    fun get(id: Long): Message
    fun use(messages: List<Message>)
    fun use(message: Message)
    fun use(recipient: Agent)
    fun use(messages: List<Message>, recipient: Agent)
}