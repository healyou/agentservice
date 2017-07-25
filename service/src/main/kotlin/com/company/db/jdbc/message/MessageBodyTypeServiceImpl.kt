package com.company.db.jdbc.message

import com.company.db.core.message.MessageBodyType
import com.company.db.core.message.MessageBodyType.Code
import com.company.db.core.message.MessageBodyTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
class MessageBodyTypeServiceImpl : MessageBodyTypeService {

    @Autowired
    private lateinit var dao: MessageBodyTypeDao

    override fun get(): List<MessageBodyType> {
        return dao.get()
    }

    override fun get(code: Code): MessageBodyType {
        return dao.get(code)
    }
}