package com.company.db.jdbc.agent

import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentService
import com.company.db.core.sc.AgentSC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author Nikita Gorodilov
 */
@Component
open class AgentServiceImpl: AgentService {

    @Autowired
    private lateinit var dao: AgentDao

    override fun create(agent: Agent): Long {
        return dao.create(agent)
    }

    override fun update(agent: Agent): Long {
        return dao.update(agent)
    }

    override fun delete(id: Long) {
        dao.delete(id)
    }

    override fun get(agentSC: AgentSC): List<Agent> {
        return dao.get(agentSC)
    }

    override fun get(id: Long): Agent {
        return dao.get(id)
    }

    override fun getByMasId(masId: String): Agent {
        return dao.getByMasId(masId)
    }

    override fun isExistsAgent(masId: String): Boolean {
        return dao.isExistsAgent(masId)
    }
}