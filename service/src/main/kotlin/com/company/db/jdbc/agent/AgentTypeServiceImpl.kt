package com.company.db.jdbc.agent

import com.company.db.core.agent.AgentType
import com.company.db.core.agent.AgentTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class AgentTypeServiceImpl: AgentTypeService {

    @Autowired
    private lateinit var dao: AgentTypeDao

    override fun get(): List<AgentType> {
        return dao.get()
    }

    override fun getByCode(code: String): AgentType {
        return dao.getByCode(code)
    }
}