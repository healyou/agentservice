package com.company.db.jdbc.message

import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageType
import com.company.db.core.message.MessageTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class MessageTypeServiceImpl : MessageTypeService {

    @Autowired
    private lateinit var dao: MessageTypeDao

    override fun get(messageGoalType: MessageGoalType): List<MessageType> {
        return dao.get(messageGoalType)
    }

    override fun get(code: MessageType.Code): MessageType {
        return dao.get(code)
    }
}