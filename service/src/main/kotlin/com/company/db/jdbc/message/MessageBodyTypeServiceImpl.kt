package com.company.db.jdbc.message

import com.company.db.core.message.MessageBodyType
import com.company.db.core.message.MessageBodyTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class MessageBodyTypeServiceImpl: MessageBodyTypeService {

    @Autowired
    private lateinit var dao: MessageBodyTypeDao

    override fun get(): List<MessageBodyType> {
        return dao.get()
    }

    override fun getByCode(code: String): MessageBodyType {
        return dao.getByCode(code)
    }
}