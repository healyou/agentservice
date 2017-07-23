package com.company.db.jdbc.agent

import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class AgentServiceImpl: AgentService {

    @Autowired
    private lateinit var dao: AgentDao

    override fun create(agent: Agent) {
        // todo
    }

    override fun update(agent: Agent) {
        // todo
    }

    override fun delete(id: Long) {
        // todo
    }

    override fun get(): List<Agent> {
        return dao.get()
    }

    override fun get(id: Long): Agent {
        return dao.get(id)
    }
}