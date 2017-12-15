package com.company.db.jdbc.message

import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageGoalTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class MessageGoalTypeServiceImpl: MessageGoalTypeService {

    @Autowired
    private lateinit var dao: MessageGoalTypeDao

    override fun get(): List<MessageGoalType> {
        return dao.get()
    }

    override fun getByCode(code: String): MessageGoalType {
        return dao.getByCode(code)
    }
}