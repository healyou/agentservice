package com.company.db.message

import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentType
import com.company.db.core.message.MessageRecipient
import com.company.objects.TypesObjects
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Тестирование функциональности класса MessageRecipient
 *
 * @author Nikita Gorodilov
 */
class MessageRecipientTest : Assert() {

    private lateinit var messageRecipient: MessageRecipient

    /* Параметры */
    private val id = 1L
    private val recipient = createTestAgent()
    private val viewedDate = Date(System.currentTimeMillis())

    @Before
    fun setup() {
        messageRecipient = MessageRecipient(
                id,
                recipient,
                viewedDate
        )
    }

    @Test
    fun testTypeGetData() {
        assertEquals(id, messageRecipient.id)
        assertEquals(recipient, messageRecipient.recipient)
        assertEquals(viewedDate, messageRecipient.viewedDate)
    }

    @Test
    fun testTypeSetDate() {
        /* новые значение */
        val newId = 2L
        val newRecipient = createTestAgent()
        val newViewedDate = Date(System.currentTimeMillis())

        messageRecipient.id = newId
        messageRecipient.recipient = newRecipient
        messageRecipient.viewedDate = newViewedDate

        assertEquals(newId, messageRecipient.id!!)
        assertEquals(newRecipient, messageRecipient.recipient)
        assertEquals(newViewedDate, messageRecipient.viewedDate)
    }

    /* создание тестового агента */
    private fun createTestAgent(): Agent {
        return Agent(
                1L,
                UUID.randomUUID().toString(),
                "name",
                AgentType(
                        1L,
                        TypesObjects.testAgentCode1,
                        "name",
                        false
                ),
                Date(System.currentTimeMillis()),
                false
        )
    }
}